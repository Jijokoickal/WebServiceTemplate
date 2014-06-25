/**
 * 
 */
package com.example.wservice.io;


/**
 * Represents the HTTP communication result.
 * 
 * @author 321930
 *
 */
public class CommResult {
	
	public static final int RESULT_NO_ERROR = 0;
	public static final int RESULT_HTTP_CONN_FAILED = 1;
	public static final int RESULT_SERVER_UNAVAILABLE = 2;
	
	public static final int RESULT_REQUEST_COMPOSE_FAILED = 3;
	public static final int RESULT_RESPONSE_PARSE_FAILED = 4;
	
	
	public static final int RESULT_SERVICE_NOT_DEFINED = 100;
	public static final int RESULT_UNKNOWN_ERROR = 255;

	
	
	
	
	public static CommResult SUCCESS = new CommResult(RESULT_NO_ERROR, "Success");

	
	
	private int resultCode;
	/**
	 * @return the resultCode
	 */
	public int getResultCode() {
		return resultCode;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	private String description;
	/**
	 * 
	 */
	public CommResult(int resultCode,String description) {
		this.resultCode=resultCode;
		this.description=description;
	}

	public String toString() {
		return "{ CommResult- resultCode: "+resultCode+" description: "+description+" }";
	}
}
