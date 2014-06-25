/**
 * 
 */
package com.example.wservice.io;

/**
 * @author 321930
 *
 */
public class Data {
	
	private Object dataObject = null;	
	private Class<?> dataType = null;
	
	
	public Object getDataObject() {
		return dataObject;
	}

	public Class<?> getDataType() {
		return dataType;
	}

	/**
	 * 
	 */
	public Data(Object dataObject,Class<?> dataType) {
		this.dataObject = dataObject;
		this.dataType = dataType;
	}

}
