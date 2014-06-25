package com.example.wservice.io.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
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
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.wservice.io.Data;
import com.example.wservice.io.DataExchanger;
import com.example.wservice.io.ProxyDetails;
import com.example.wservice.io.RequestType;
import com.example.wservice.io.exception.DataExchangerException;


/**
 * @author 321930
 *
 */
public class DataExchangerImpl implements DataExchanger {
	
	
	private ProxyDetails proxyDetails = null;


	// constructor
	public DataExchangerImpl() {

	}
	public DataExchangerImpl( ProxyDetails proxyDetails) {
		this.proxyDetails=proxyDetails;
	}

	public String exchangeStringGET(String url, List<NameValuePair> params) throws ClientProtocolException,IOException{

		InputStream is = null;
		StringBuilder sb = new StringBuilder();


			// check for request method
			DefaultHttpClient httpClient = null;
			
			if(proxyDetails!=null && proxyDetails.isProxyEnabled()){
				httpClient = getHttpClientWithProxy();
			}else{
				httpClient = getDefaultHttpClient();
			}
			
			
			if (params != null) {
				String paramString = URLEncodedUtils.format(params, "utf-8");
				if (paramString != null && paramString.length() > 0)
					url += "?" + paramString;
			}
			HttpGet httpGet = new HttpGet(url);

			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();

			if (is == null)
				return null;

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);

			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
		return sb.toString();
	}

	public String exchangeStringPOST(String url, String dataStr,
			String contentType) throws ClientProtocolException,IOException{

		InputStream is = null;
		StringBuilder sb = null;

			DefaultHttpClient httpClient = null;
			
			if(proxyDetails!=null && proxyDetails.isProxyEnabled()){
				httpClient = getHttpClientWithProxy();
			}else{
				httpClient = getDefaultHttpClient();
			}
			

			HttpPost httpPost = new HttpPost(url);

			if (contentType != null)
				httpPost.setHeader("Content-type", contentType); // "application/json"

			ByteArrayEntity se = new ByteArrayEntity(dataStr.getBytes("UTF8"));
/*			if (contentType != null)
				se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
						contentType));
*/			httpPost.setEntity(se);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();

			if (is == null)
				return null;

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();

		return sb.toString();

	}

	public DefaultHttpClient getDefaultHttpClient() {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		BasicHttpParams basicHttpParams = new BasicHttpParams();
		HttpProtocolParams.setContentCharset(basicHttpParams, "utf-8");
		int timeoutConnection = 20000;
		HttpConnectionParams.setConnectionTimeout(basicHttpParams,
				timeoutConnection);
		HttpConnectionParams.setSoTimeout(basicHttpParams, timeoutConnection);
		return httpClient;
	}

	public DefaultHttpClient getHttpClientWithProxy() {
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
		HttpHost proxy = new HttpHost(proxyDetails.getServerIp(), proxyDetails.getPort());
		params.setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		DefaultHttpClient httpClient = new DefaultHttpClient(manager, params);
		httpClient.getCredentialsProvider().setCredentials(
				new AuthScope(proxyDetails.getServerIp(), proxyDetails.getPort()),
				new UsernamePasswordCredentials(proxyDetails.getUserName(), proxyDetails.getPassword()));
		return httpClient;
	}

	@Override
	public Data exchangeDataWithServer(String url, Data data, RequestType requestType) throws DataExchangerException ,ClientProtocolException,IOException{
		if (data.getDataType() == String.class) {
			String resp =null;
			if(requestType == RequestType.POST)
				resp = exchangeStringPOST(url, (String)data.getDataObject(), null);
			else
				resp = exchangeStringGET(url,  null);
			
			return new Data(resp, String.class);
			
		} else 	if (data.getDataType() == JSONObject.class) {
			String resp =null;
			if(requestType == RequestType.POST){
				String jsonStr = ((JSONObject)data.getDataObject()).toString();
				resp = exchangeStringPOST(url, jsonStr, "application/json");
			}
			else{
				resp = exchangeStringGET(url,  null);
			}
			
			Log.d("DataExchangerImpl", "Response: "+resp);
			
			try {
				JSONObject jObj = new JSONObject(resp);
				return new Data(jObj, JSONObject.class);
			} catch (JSONException e) {
				throw new DataExchangerException("Failed to parse the response to JSONObject");
			}
			
		}else {
			throw new DataExchangerException("Exchanging of data type "
					+ data.getDataType().getName() + " is not supported");
		}
	}

}
