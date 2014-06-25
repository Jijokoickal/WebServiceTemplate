package com.example.wservice.io.impl;

import com.example.wservice.io.CallbackServiceResponseHandler;
import com.example.wservice.io.ws.request.BaseRequestData;
import com.example.wservice.io.ws.response.BaseResponseData;

class RequestHolder {

	BaseRequestData serviceRequest = null;
	CallbackServiceResponseHandler callbackServiceResponseHandler = null;

	public RequestHolder(BaseRequestData serviceRequest,
			CallbackServiceResponseHandler callbackServiceResponseHandler) {
		// TODO Auto-generated constructor stub
		this.serviceRequest = serviceRequest;
		this.callbackServiceResponseHandler = callbackServiceResponseHandler;
	}

}
