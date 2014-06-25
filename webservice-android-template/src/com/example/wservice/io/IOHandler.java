/**
 * 
 */
package com.example.wservice.io;

import com.example.wservice.io.ws.request.BaseRequestData;

/**
 * Interface represents the handling of IO request/response ( webservice ).
 * 
 * @author 321930
 * 
 */
public interface IOHandler {

	/**
	 * Method to perform asynchronous webservice communication.
	 * 
	 * @param serviceRequest
	 *            the request
	 * @param callbackServiceResponseHandler
	 *            the callback instance.
	 */
	public void doServiceRequest(final BaseRequestData serviceRequest,
			final CallbackServiceResponseHandler callbackServiceResponseHandler);

}
