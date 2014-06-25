/**
 * 
 */
package com.example.wservice.io.impl;

import com.example.wservice.io.CallbackServiceResponseHandler;
import com.example.wservice.io.IOHandler;
import com.example.wservice.io.ws.request.BaseRequestData;
import com.example.wservice.io.ws.response.BaseResponseData;

public class IOHandlerImpl implements IOHandler {

	@Override
	public void doServiceRequest(BaseRequestData serviceRequest,
			CallbackServiceResponseHandler callbackServiceResponseHandler) {
		// TODO Auto-generated method stub
		new DataExchangerTask(new RequestHolder(serviceRequest,
				callbackServiceResponseHandler)).execute();
	}
}
