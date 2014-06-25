package com.example.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.util.AppUtil;
import com.example.util.Constants;
import com.example.wservice.io.CallbackServiceResponseHandler;
import com.example.wservice.io.CommStatus;
import com.example.wservice.io.ws.request.BaseRequestData;
import com.example.wservice.io.ws.response.BaseResponseData;

public abstract class BaseActivity extends Activity implements
		CallbackServiceResponseHandler {

	private static final String TAG = BaseActivity.class.getSimpleName();

	private ProgressDialog progressDialog;
	private String progressTitle;
	private boolean progressVisible = true;
	private boolean isProgressDestroyed;
	public boolean isActivityRunning = false;

	private AlertDialog alertDialog;
	private AlertDialog.Builder alertDialogBuilder;

	@Override
	public void onStart() {
		super.onStart();
		isActivityRunning = true;
		// decryptDatabase();
	}

	@Override
	protected void onDestroy() {
		isProgressDestroyed = true;
		super.onDestroy();
	}

	public void setProgressTitle(String title) {
		progressTitle = title;
	}

	public void ShowProgress() {
		if (progressDialog == null) {
			progressDialog = new ProgressDialog(this);
			progressDialog.setCancelable(false);
		}
		if (progressTitle != null) {
			progressDialog.setMessage(progressTitle);
		} else {
			progressDialog.setMessage(Constants.LOADING);
		}
		if (!progressDialog.isShowing() && progressVisible
				&& !isProgressDestroyed) {
			AppUtil.hideKeyBoard(this);
			progressDialog.show();
		}
	}

	public void hideProgress() {
		Log.d(TAG, this.getClass().getSimpleName());
		if (progressDialog != null && progressDialog.isShowing()
				&& !isProgressDestroyed)
			progressDialog.dismiss();
		progressDialog = null;
	}

	protected void setCancelable(
			DialogInterface.OnCancelListener onCancelListener) {
		if (progressDialog != null) {
			progressDialog.setCancelable(true);
			progressDialog.setCanceledOnTouchOutside(false);
			progressDialog.setOnCancelListener(onCancelListener);
		}
	}

	public void doServiceCall(BaseRequestData requestData,
			CallbackServiceResponseHandler handler) {
		ShowProgress();
		switch (requestData.getRequestCode()) {

		case Constants.HTTP_COMM_CODE:

			break;

		}

	}

	private Handler toastHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				String message = (String) msg.obj;
				alertDialogBuilder = new AlertDialog.Builder(BaseActivity.this);
				alertDialog = alertDialogBuilder.create();

				alertDialogBuilder.setTitle("App Name");
				alertDialogBuilder.setMessage(message);

				alertDialogBuilder.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog, int id) {
								alertDialog.dismiss();
							}
						});

				alertDialog.show();

				break;
			}
		}
	};

	@Override
	public void handleServiceResponse(CommStatus commStatus,
			BaseResponseData responseData) {
		if (commStatus == CommStatus.SUCCESS) {

			/*
			 * switch (serviceResponseData.getCommunicationCode()) { case
			 * Constants.HTTP_COMM_CODE: }
			 */
			onSuccessResponse(commStatus, responseData);

		} else {
			onFailureResponse(commStatus, responseData);
		}
	}

	public void onFailureResponse(CommStatus commStatus,
			BaseResponseData serviceResponseData) {
		hideProgress();
		Message msg = new Message();
		msg.what = 0;
		msg.obj = commStatus.getDescription();
		if (isActivityRunning) {
			if (alertDialog != null && !alertDialog.isShowing()) {
				toastHandler.sendMessage(msg);
			} else if (alertDialog == null) {
				toastHandler.sendMessage(msg);
			}
		}
	}

	public abstract void onSuccessResponse(CommStatus commStatus,
			BaseResponseData responseData);
}
