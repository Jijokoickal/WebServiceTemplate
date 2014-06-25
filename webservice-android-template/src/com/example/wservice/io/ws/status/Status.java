package com.example.wservice.io.ws.status;

public class Status {

	int code;
	String message;
	String sCode;

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Status(int code, String message) {
		// TODO Auto-generated constructor stub
		this.code = code;
		this.message = message;

	}

	public Status(String sCode, String message) {
		// TODO Auto-generated constructor stub
		this.sCode = sCode;
		this.message = message;

	}

	public Status(int code, String sCode, String message) {
		// TODO Auto-generated constructor stub
		this.code = code;
		this.sCode = sCode;
		this.message = message;

	}
}
