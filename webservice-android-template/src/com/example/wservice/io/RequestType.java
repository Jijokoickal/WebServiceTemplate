/**
 * 
 */
package com.example.wservice.io;

/**
 * @author 321930
 *
 */
public class RequestType {
	
	public static final RequestType GET = new RequestType("GET");
	public static final RequestType POST = new RequestType("POST");
	
	
	private String type=null;

	public String getType() {
		return type;
	}

	/**
	 * 
	 */
	private RequestType(String type) {
		this.type=type;
	}

}
