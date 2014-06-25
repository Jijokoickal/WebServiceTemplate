package com.example.wservice.io.ws.status;

public class LoginStatus extends Status {

	public LoginStatus(int code, String sCode, String message) {
		super(code, sCode, message);
		// TODO Auto-generated constructor stub
	}

	public static LoginStatus STAT_SUCCESS = new LoginStatus(0, null, "Success");
	public static LoginStatus LOGIN_ERROR = new LoginStatus(1, null, "Error");
}
