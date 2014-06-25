/**
 * 
 */
package com.example.wservice.io;

import com.example.wservice.io.ws.response.BaseResponseData;


public interface CallbackServiceResponseHandler {

	/**
	 * This method would be called when a webservice request/response is completed.
	 * @param serviceResponseData the webservice response data.
	 */
	public void handleServiceResponse(CommStatus commStatus,BaseResponseData serviceResponseData);
	
	
}
