/**
 * 
 */
package com.example.wservice.io.ws.request;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.JsonMappingException;

import com.example.App;
import com.example.wservice.io.Data;
import com.example.wservice.io.RequestType;

/**
 * @author 321930
 * 
 */
public class LoginRequestData extends BaseRequestData {

	public String reqClass = "AuthenticateRequest";

	private String requestName;
	private int requestCode;
	private RequestType requestType;

	private String userName, password, product;

	public LoginRequestData(RequestType reqType, int reqCode, String userName,
			String password, String product) {
		this.requestType = reqType;
		this.requestCode = reqCode;
		this.userName = userName;
		this.password = password;
		this.product = product;
	}

	@Override
	public String getUrl() {
		return App.getInstance().getAppProperties().getLoginUrl();
	}

	@Override
	@JsonIgnore
	public Data getJSONRequest() throws JsonGenerationException,
			JsonMappingException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRequestName() {
		return requestName;
	}

	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}

	public int getRequestCode() {
		return requestCode;
	}

	public void setRequestCode(int requestCode) {
		this.requestCode = requestCode;
	}

	public void setReqClass(String reqClass) {
		this.reqClass = reqClass;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getProduct() {
		return product;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	@Override
	public RequestType getRequestType() {
		return RequestType.POST;
	}

	public String getReqClass() {
		return reqClass;
	}

}
