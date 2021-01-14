package com.app.dto;

public class AuthenticationRequest {
	private String userName, password;

	public AuthenticationRequest() {
		System.out.println("In Constructor of "+getClass().getName());
	}

	public AuthenticationRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		System.out.println("In get Username "+userName);
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		System.out.println("In get password "+password);
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
