package com.example.wservice.io.impl;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.client.ClientProtocolException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import android.os.AsyncTask;
import android.util.Log;

import com.example.util.Logger;
import com.example.wservice.io.CommStatus;
import com.example.wservice.io.Data;
import com.example.wservice.io.DataExchanger;
import com.example.wservice.io.NetworkConfig;
import com.example.wservice.io.ServiceConfig;
import com.example.wservice.io.ServiceDef;
import com.example.wservice.io.exception.DataExchangerException;
import com.example.wservice.io.ws.response.BaseResponseData;

class DataExchangerTask extends AsyncTask<String, String, BaseResponseData> {

	private static final String TAG = "ServerDataExchanger";
	private static final long CONNECTION_TIMEOUT_MILLISECONDS = 60 * 1000L;

	private Object lock = new Object();

	private ServiceDef serviceDef = null;
	private Class<?> reqDataClass;
	private Class<?> respDataClass;
	private RequestHolder requestHolder;

	private boolean exchangeTaskOver = false;

	private Timer timer = null;
	private BaseResponseData serviceResponseData = null;
	private CommStatus commResult = null;

	public DataExchangerTask(RequestHolder requestHolder) {
		this.requestHolder = requestHolder;
		timer = new Timer();
		timer.schedule(checkCommunicationTask, CONNECTION_TIMEOUT_MILLISECONDS);
	}

	private synchronized void doResultCallback() {
		synchronized (lock) {
			if (exchangeTaskOver)
				return;
			exchangeTaskOver = true;

			try {
				requestHolder.callbackServiceResponseHandler
						.handleServiceResponse(commResult, serviceResponseData);
			} catch (Throwable e) {
				Log.e(TAG,
						"*********** handleServiceResponse() produces exception *************");
				e.printStackTrace();

			}
			try {
				this.cancel(true);
			} catch (Exception e) {
			}
			try {
				timer.cancel();
			} catch (Exception e) {
			}
		}
	}

	private TimerTask checkCommunicationTask = new TimerTask() {

		@Override
		public void run() {
			commResult = new CommStatus(CommStatus.STATUS_CONNECTION_TIMED_OUT,
					"Connection timed out!!");
			serviceResponseData = getErrorInstance();

			doResultCallback();
		}
	};

	@Override
	protected void onPreExecute() {

		try {

			for (ServiceDef def : ServiceConfig.getServices()) {
				reqDataClass = Class.forName(def
						.getVal(ServiceDef.KEY_CLASSNAME_REQUEST_DATA));
				if (reqDataClass == requestHolder.serviceRequest.getClass()) {
					respDataClass = Class.forName(def
							.getVal(ServiceDef.KEY_CLASSNAME_RESPONSE_DATA));
					Log.d(TAG,
							this.toString() + ", "
									+ "Sevice definition Found. serviceName= "
									+ def.getServiceName());
					serviceDef = def;
					break;
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected BaseResponseData doInBackground(String... params) {
		if (serviceDef == null) {
			Log.e(TAG, "Sevice definition NOT FOUND. serviceName= "
					+ serviceDef.getServiceName());

			commResult = new CommStatus(CommStatus.STATUS_SERVICE_NOT_DEFINED,
					"Service definition NOT FOUND");

			return null;
		}

		try {

			DataExchanger dataExchanger = new DataExchangerImpl(
					NetworkConfig.getProxyDetails());

			Logger.d("Request URL String-->",
					requestHolder.serviceRequest.getUrl());

			Logger.d("Request JSON String-->", requestHolder.serviceRequest
					.getJSONRequest().getDataObject().toString());

			Data data = dataExchanger.exchangeDataWithServer(
					requestHolder.serviceRequest.getUrl(),
					requestHolder.serviceRequest.getJSONRequest(),
					requestHolder.serviceRequest.getRequestType());

			serviceResponseData = (BaseResponseData) respDataClass
					.newInstance();
			serviceResponseData = (BaseResponseData) new ObjectMapper()
					.readValue(data.getDataObject().toString(), respDataClass);
			serviceResponseData
					.setCommunicationCode(requestHolder.serviceRequest
							.getRequestCode());
			// serviceResponseData.setData(data);
			commResult = CommStatus.SUCCESS;

		} catch (ClientProtocolException e) {
			commResult = new CommStatus(CommStatus.STATUS_HTTP_CONN_FAILED,
					"Connection failed. Please check the internet connection and try again.");
			serviceResponseData = getErrorInstance();
		} catch (DataExchangerException e) {
			commResult = new CommStatus(
					CommStatus.STATUS_RESPONSE_DATA_EXCHANGE_ERROR,
					"Invalid response from server.");
			serviceResponseData = getErrorInstance();
		} catch (JsonParseException e) {
			commResult = new CommStatus(
					CommStatus.STATUS_RESPONSE_PARSE_FAILED,
					"Invalid response from server.");
			serviceResponseData = getErrorInstance();
			e.printStackTrace();
		} catch (JsonMappingException e) {
			commResult = new CommStatus(
					CommStatus.STATUS_RESPONSE_PARSE_FAILED,
					"Invalid response from server.");
			serviceResponseData = getErrorInstance();
			e.printStackTrace();
		} catch (IOException e) {
			commResult = new CommStatus(CommStatus.STATUS_HTTP_CONN_FAILED,
					"Connection failed. Please check the internet connection and try again.");
			serviceResponseData = getErrorInstance();
		} catch (Throwable e) {
			commResult = new CommStatus(CommStatus.STATUS_UNKNOWN_ERROR,
					"Unknown error - " + e.getMessage());
			serviceResponseData = getErrorInstance();
			e.printStackTrace();

		}

		return serviceResponseData;
	}

	private BaseResponseData getErrorInstance() {
		BaseResponseData errorInstance = null;
		try {
			errorInstance = (BaseResponseData) respDataClass.newInstance();
			errorInstance.setCommunicationCode(requestHolder.serviceRequest
					.getRequestCode());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return errorInstance;
	}

	@Override
	protected void onPostExecute(BaseResponseData serviceResponseData) {
		doResultCallback();
	}

}
