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
public class CommStatus {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + resultCode;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommStatus other = (CommStatus) obj;
		if (resultCode != other.resultCode)
			return false;
		return true;
	}
	public static final int STATUS_NO_ERROR = 0;
	public static final int STATUS_HTTP_CONN_FAILED = 1;
	public static final int STATUS_SERVER_UNAVAILABLE = 2;
	
	public static final int STATUS_REQUEST_COMPOSE_FAILED = 3;
	public static final int STATUS_RESPONSE_PARSE_FAILED = 4;
	
	public static final int STATUS_RESPONSE_DATA_EXCHANGE_ERROR = 5;
	
	public static final int STATUS_CONNECTION_TIMED_OUT = 100;
	public static final int STATUS_SERVICE_NOT_DEFINED = 101;
	public static final int STATUS_UNKNOWN_ERROR = 255;

	
	
	
	
	public static CommStatus SUCCESS = new CommStatus(STATUS_NO_ERROR, "Success");

	
	
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
	public CommStatus(int resultCode,String description) {
		this.resultCode=resultCode;
		this.description=description;
	}
	
	public boolean isCommunicationSuccess(){
		return this.resultCode == STATUS_NO_ERROR;
	}

	public String toString() {
		return "{ CommResult- resultCode: "+resultCode+" description: "+description+" }";
	}
}
