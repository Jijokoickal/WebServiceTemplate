package com.example.wservice.io;

import com.example.wservice.io.data.ServiceRequestData;
import com.example.wservice.io.exception.RequestGenerationException;

/**
 * Class which is responsible for composing webservice requests.
 * 
 * @author 321930
 * 
 */
public interface RequestComposer {

	
	/**
	 * method which generates a JSON request for a given requestData instance.
	 * 
	 * @param serviceRequest the requestData object
	 * @return the soapReqData
	 */
	public Data generateRequest(ServiceRequestData serviceRequest) throws RequestGenerationException;


}
