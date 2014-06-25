/**
 * 
 */
package com.example.wservice.io;

import java.util.Vector;


/**
 * @author 321930
 *
 */
public class ServiceConfig {
	private static Vector<ServiceDef> services = new Vector<ServiceDef>();
	
	
	
	
	
	public static synchronized void addService(ServiceDef services) {
		ServiceConfig.services.add(services);
	}

	public static synchronized Vector<ServiceDef> getServices() {
		return ServiceConfig.services;
	}



	/**
	 * 
	 */
	private ServiceConfig() {}

}
