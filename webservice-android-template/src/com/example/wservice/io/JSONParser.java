package com.example.wservice.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {

	private static final boolean MOBILE_DATA = true;


	// constructor
	public JSONParser() {

	}

	public String makeHttpGetRequest(String url, List<NameValuePair> params) {

		InputStream is = null;
		String response = null;

		// Making HTTP request
		try {

			// check for request method
			DefaultHttpClient httpClient = null;
			if (MOBILE_DATA) {
				httpClient = getDataHttpClient();
			} else {
				httpClient = getHttpClient();
			}
			String paramString = URLEncodedUtils.format(params, "utf-8");
			if (paramString != null && paramString.length() > 0)
				url += "?" + paramString;
			HttpGet httpGet = new HttpGet(url);

			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();

			if (is == null)
				return null;

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			response = sb.toString();
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}

		// return JSON String
		return response;

	}

	// function get json from url
	// by making HTTP POST or GET mehtod
	public JSONObject makeHttpRequest(String url, String method,
			List<NameValuePair> params) {

		InputStream is = null;
		JSONObject jObj = null;
		String json = "";
		// Making HTTP request
		try {

			// check for request method
			if (method == "POST") {
				// request method is POST
				// defaultHttpClient
				DefaultHttpClient httpClient = null;
				if (MOBILE_DATA) {
					httpClient = getDataHttpClient();
				} else {
					httpClient = getHttpClient();
				}

				//

				HttpPost httpPost = new HttpPost(url);
				httpPost.setEntity(new UrlEncodedFormEntity(params));

				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();

			} else if (method == "GET") {
				DefaultHttpClient httpClient = null;
				if (MOBILE_DATA) {
					httpClient = getDataHttpClient();
				} else {
					httpClient = getHttpClient();
				}
				String paramString = URLEncodedUtils.format(params, "utf-8");
				if (paramString != null && paramString.length() > 0)
					url += "?" + paramString;
				HttpGet httpGet = new HttpGet(url);

				HttpResponse httpResponse = httpClient.execute(httpGet);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (is == null)
			return null;

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}

		// try parse the string to a JSON object
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}

		// return JSON String
		return jObj;

	}

	public DefaultHttpClient getDataHttpClient() {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		BasicHttpParams basicHttpParams = new BasicHttpParams();
		HttpProtocolParams.setContentCharset(basicHttpParams, "utf-8");
		int timeoutConnection = 20000;
		HttpConnectionParams.setConnectionTimeout(basicHttpParams,
				timeoutConnection);
		return httpClient;
	}

	// function get json from url
	// by making HTTP POST or GET mehtod
	public JSONObject makeHttpRequestPOST(String url, JSONObject jsonObject) {

		InputStream is = null;
		JSONObject jObj = null;
		String json = "";
		// Making HTTP request
		try {

			// check for request method
			// request method is POST
			DefaultHttpClient httpClient = null;
			if (MOBILE_DATA) {
				httpClient = getDataHttpClient();
			} else {
				httpClient = getHttpClient();
			}
			HttpPost httppost = new HttpPost(url);
			httppost.setHeader("Content-type", "application/json");
			ByteArrayEntity se = new ByteArrayEntity(jsonObject.toString()
					.getBytes("UTF8"));

			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
					"application/json"));
			httppost.setEntity(se);

			HttpResponse httpResponse = httpClient.execute(httppost);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());

		}

		// try parse the string to a JSON object
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
			json = "{ \"response\":" + json + "}";
			try {
				jObj = new JSONObject(json);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		Log.d("", "" + jObj.toString());
		// return JSON String
		return jObj;

	}

	public DefaultHttpClient getHttpClient() {
		// SETS UP PARAMETERS
		BasicHttpParams params = new BasicHttpParams();
		// HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, "utf-8");
		// params.setBooleanParameter("http.protocol.expect-continue", false);
		// HttpHost proxy = new HttpHost("10.230.193.81", 6050);
		// params.setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		int timeoutConnection = 20000;
		HttpConnectionParams.setConnectionTimeout(params, timeoutConnection);
		// Set the default socket timeout (SO_TIMEOUT)
		// in milliseconds which is the timeout for waiting for data.
		int timeoutSocket = 45000;
		HttpConnectionParams.setSoTimeout(params, timeoutSocket);
		// REGISTERS SCHEMES FOR BOTH HTTP AND HTTPS
		SchemeRegistry registry = new SchemeRegistry();
		registry.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		final org.apache.http.conn.ssl.SSLSocketFactory sslSocketFactory = org.apache.http.conn.ssl.SSLSocketFactory
				.getSocketFactory();
		sslSocketFactory
				.setHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		registry.register(new Scheme("https", sslSocketFactory, 443));
		ThreadSafeClientConnManager manager = new ThreadSafeClientConnManager(
				params, registry);
		HttpHost proxy = new HttpHost("10.230.193.81", 6050);
		params.setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		DefaultHttpClient httpClient = new DefaultHttpClient(manager, params);
		httpClient.getCredentialsProvider().setCredentials(
				new AuthScope("10.230.193.81", 6050),
				new UsernamePasswordCredentials("321930", "phone-456"));
		return httpClient;
	}

}
