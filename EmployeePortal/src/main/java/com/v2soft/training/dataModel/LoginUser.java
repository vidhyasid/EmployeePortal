package com.v2soft.training.dataModel;

public class LoginUser {
	private String userName;
	private String password;
	
	public LoginUser() {
		
	}
	
	public LoginUser(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
