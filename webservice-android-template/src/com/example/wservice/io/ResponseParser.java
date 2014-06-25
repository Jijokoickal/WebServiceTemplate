package com.example.wservice.io;

import com.example.wservice.io.data.ServiceResponseData;
import com.example.wservice.io.exception.ResponseParserException;


public interface ResponseParser {
	
	

	public ServiceResponseData parseResponse(Data data) throws ResponseParserException;
	
	
}
