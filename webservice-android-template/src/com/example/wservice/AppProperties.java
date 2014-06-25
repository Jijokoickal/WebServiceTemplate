/**
 * 
 */
package com.example.wservice;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.example.wservice.io.ProxyDetails;

/**
 * @author 321930
 * 
 */
public class AppProperties {

	private String loginUrl;

	public synchronized ProxyDetails getProxyDetails() {
		return proxyDetails;
	}

	/**
	 * @return the loginUrl
	 */
	public synchronized String getLoginUrl() {
		return loginUrl;
	}

	private ProxyDetails proxyDetails;

	/**
	 * 
	 */
	public AppProperties() {
		// TODO Auto-generated constructor stub
	}

	public static AppProperties loadFromPropertiesFile(InputStream inputStream) {
		AppProperties appProperties = new AppProperties();

		try {
			Properties properties = new Properties();
			properties.load(inputStream);

			appProperties.loginUrl = properties.getProperty("loginserviceUrl",
					"");

			String proxyIp = properties.getProperty("proxyServerIp", "");
			int proxyPort = 0;
			try {
				proxyPort = Integer.parseInt(properties.getProperty(
						"proxyPort", "0"));
			} catch (NumberFormatException e) {
			}
			String proxyUsername = properties.getProperty("proxyUsername", "");
			String proxyPass = properties.getProperty("proxyPassword", "");
			boolean isProxyEnabled = properties.getProperty("isProxyEnabled",
					"false").equalsIgnoreCase("true");

			appProperties.proxyDetails = new ProxyDetails(isProxyEnabled,
					proxyIp, proxyPort, proxyUsername, proxyPass);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return appProperties;

	}

}
