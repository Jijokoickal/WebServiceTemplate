/**
 * 
 */
package com.example.wservice.io.ws;

import org.json.JSONObject;

import com.example.wservice.io.Data;
import com.example.wservice.io.RequestComposer;
import com.example.wservice.io.data.ServiceRequestData;
import com.example.wservice.io.exception.RequestGenerationException;
import com.example.wservice.io.ws.request.LoginRequestData;

/**
 * @author 321930
 * 
 */
public class LoginRequestComposer implements RequestComposer {

	/**
	 * 
	 */
	public LoginRequestComposer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Data generateRequest(ServiceRequestData serviceRequest)
			throws RequestGenerationException {

		try {
			
			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put("UserName",
					((LoginRequestData) serviceRequest).getUserName());
			jsonObject.put("Password",
					((LoginRequestData) serviceRequest).getPassword());
			jsonObject.put("Product",
					((LoginRequestData) serviceRequest).getPassword());
			
			return new Data(jsonObject, JSONObject.class);
			
		} catch (Throwable e) {
			throw new RequestGenerationException(
					"Ex while generating request- " + e.getMessage());
		}

	}

}
