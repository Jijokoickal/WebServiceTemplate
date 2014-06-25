package com.example.wservice.io.ws.response;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.example.wservice.io.ws.status.Status;

public abstract class BaseResponseData {

	public int communicationCode;

	public int getCommunicationCode() {
		return communicationCode;
	}

	public void setCommunicationCode(int communicationCode) {
		this.communicationCode = communicationCode;
	}

	@JsonIgnore
	public abstract Status getStatus();
}
