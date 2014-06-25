/**
 * 
 */
package com.example.wservice.io.data;

import com.example.wservice.io.RequestType;

/**
 * Represents an abstract webservice request data
 * 
 * @author 321930
 * 
 */
public interface ServiceRequestData {

	public RequestType getRequestType();

	public String getUrl();

}
