/**
 * 
 */
package com.example.wservice.io;


/**
 * @author 321930
 *
 */
public class ProxyDetails {
	
	public ProxyDetails() {
		// TODO Auto-generated constructor stub
	}
	
	private boolean isProxyEnabled = false;
	public boolean isProxyEnabled() {
		return isProxyEnabled;
	}


	public void setProxyEnabled(boolean isProxyEnabled) {
		this.isProxyEnabled = isProxyEnabled;
	}


	public String getServerIp() {
		return serverIp;
	}


	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}


	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	private String serverIp;
	private int port;
	private String userName;
	private String password;
	

	/**
	 * 
	 */
	public ProxyDetails(boolean isProxyEnabled,String serverIp,int port,String userName,String password) {
		this.isProxyEnabled=isProxyEnabled;
		this.serverIp=serverIp;
		this.port=port;
		this.userName=userName;
		this.password=password;
	}
	

}
