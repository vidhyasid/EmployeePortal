package com.v2soft.training.dataModel;

import javax.persistence.Column;


public class LoginSessionIdInfo implements java.io.Serializable {

	private String userName;
	private int loginStatus;

	public LoginSessionIdInfo() {
	}

	public LoginSessionIdInfo(String userName, int loginStatus) {
		this.userName = userName;
		this.loginStatus = loginStatus;
	}

	@Column(name = "userName", nullable = false, length = 20)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "login_status", nullable = false)
	public int getLoginStatus() {
		return this.loginStatus;
	}

	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof LoginSessionIdInfo))
			return false;
		LoginSessionIdInfo castOther = (LoginSessionIdInfo) other;

		return ((this.getUserName() == castOther.getUserName()) || (this.getUserName() != null
				&& castOther.getUserName() != null && this.getUserName().equals(castOther.getUserName())))
				&& (this.getLoginStatus() == castOther.getLoginStatus());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getUserName() == null ? 0 : this.getUserName().hashCode());
		result = 37 * result + this.getLoginStatus();
		return result;
	}
}
