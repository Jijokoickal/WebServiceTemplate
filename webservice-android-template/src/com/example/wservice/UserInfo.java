package com.example.wservice;

public class UserInfo {
	
	private static UserInfo instance = null;
	
	
	private String userId="";

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public static synchronized UserInfo getInstance() {
		if(instance==null)
			instance=new UserInfo();
		return instance;
	}

	private UserInfo() {}
	
	

}
