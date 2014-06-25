package com.example.wservice.io.ws.request;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.JsonMappingException;

import com.example.wservice.io.Data;
import com.example.wservice.io.RequestType;
import com.example.wservice.io.data.ServiceRequestData;

public abstract class BaseRequestData implements ServiceRequestData {

	private RequestType requestType;
	private int requestCode;
	private String requestName;

	public BaseRequestData() {
		// TODO Auto-generated constructor stub
	}

	public BaseRequestData(String inRequestName, RequestType inRequestType,
			int reqCode) {
		super();
		this.requestName = inRequestName;
		this.setRequestType(inRequestType);
		this.requestCode = reqCode;
	}

	public int getRequestCode() {
		return requestCode;
	}

	public void setRequestCode(int requestCode) {
		this.requestCode = requestCode;
	}

	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	public abstract String getUrl();

	@JsonIgnore
	public abstract Data getJSONRequest() throws JsonGenerationException,
			JsonMappingException, IOException;

}
