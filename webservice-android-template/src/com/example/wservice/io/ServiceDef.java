/**
 * 
 */
package com.example.wservice.io;

import java.util.Hashtable;


/**
 * @author 321930
 * 
 */
public class ServiceDef {

	public static final String KEY_CLASSNAME_REQUEST_DATA = "reqDataClass";
	public static final String KEY_CLASSNAME_RESPONSE_DATA = "respDataClass";

	private Hashtable<String, String> defs = new Hashtable<String, String>();

	private String serviceName;

	public String getServiceName() {

		return serviceName;
	}

	/**
	 * 
	 */
	public ServiceDef(String serviceName) {
		this.serviceName = serviceName;
	}

	public ServiceDef(String serviceName, Class<?> reqDataClass,
			Class<?> respDataClass) {
		this.serviceName = serviceName;
		putVal(ServiceDef.KEY_CLASSNAME_REQUEST_DATA, reqDataClass.getName());
		putVal(ServiceDef.KEY_CLASSNAME_RESPONSE_DATA, respDataClass.getName());
	}

	public String getVal(String key) {
		return defs.get(key);
	}

	private void putVal(String key, String val) {
		defs.put(key, val);
	}

}
