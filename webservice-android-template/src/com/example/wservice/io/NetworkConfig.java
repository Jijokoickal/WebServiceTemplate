/**
 * 
 */
package com.example.wservice.io;

/**
 * @author 321930
 *
 */
public class NetworkConfig {
	
	private static ProxyDetails proxyDetails = null;

	public static ProxyDetails getProxyDetails() {
		return proxyDetails;
	}

	public static void setProxyDetails(ProxyDetails proxyDetails) {
		NetworkConfig.proxyDetails = proxyDetails;
	}

	/**
	 * 
	 */
	private NetworkConfig() {
		// TODO Auto-generated constructor stub
	}

}
