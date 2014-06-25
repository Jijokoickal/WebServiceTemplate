package com.example.wservice.io.ws.response;

import com.example.wservice.io.data.ServiceResponseData;
import com.example.wservice.io.ws.status.LoginStatus;
import com.example.wservice.io.ws.status.Status;

public class LoginResponseData extends BaseResponseData implements
		ServiceResponseData {

	private String totalCount;
	private int responseStatusCode;
	private String responseStatusMessage;

	public String getTotalCount() {
		return totalCount;
	}

	public int getResponseStatusCode() {
		return responseStatusCode;
	}

	public String getResponseStatusMessage() {
		return responseStatusMessage;
	}

	/**
	 * 
	 */
	public LoginResponseData(String totalCount, int responseStatusCode,
			String responseStatusMessage) {

		this.totalCount = totalCount;
		this.responseStatusCode = responseStatusCode;
		this.responseStatusMessage = responseStatusMessage;

	}

	public boolean isLoginSuccess() {
		return (responseStatusCode == 200);
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		int code = responseStatusCode;

		LoginStatus status = LoginStatus.LOGIN_ERROR;
		switch (code) {
		case 0:
			status = LoginStatus.STAT_SUCCESS;
			break;
		}

		return status;
	}

}
