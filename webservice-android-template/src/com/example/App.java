/**
 * 
 */
package com.example;

import java.io.IOException;

import android.app.Application;
import android.content.res.AssetManager;

import com.example.wservice.AppProperties;
import com.example.wservice.io.NetworkConfig;
import com.example.wservice.io.ServiceConfig;
import com.example.wservice.io.ServiceDef;
import com.example.wservice.io.ws.request.LoginRequestData;
import com.example.wservice.io.ws.response.LoginResponseData;

/**
 * @author 321930
 * 
 */
public class App extends Application {

	AppProperties appProperties = null;

	public AppProperties getAppProperties() {
		return appProperties;
	}

	private static App instance = null;

	public static synchronized App getInstance() {
		return instance;
	}

	/**
	 * 
	 */
	public App() {
		instance = this;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		doConfigurations();
	}

	private void configWebservices() {

		ServiceConfig.addService(new ServiceDef("Login",
				LoginRequestData.class, LoginResponseData.class));

	}

	private void doConfigurations() {

		configWebservices();

		AssetManager assetManager = getAssets();

		try {
			appProperties = AppProperties.loadFromPropertiesFile(assetManager
					.open("app.properties"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		if (appProperties != null) {
			NetworkConfig.setProxyDetails(appProperties.getProxyDetails());
		}

	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}

}
