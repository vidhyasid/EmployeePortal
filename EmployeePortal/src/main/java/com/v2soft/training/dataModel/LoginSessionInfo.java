package com.v2soft.training.dataModel;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


public class LoginSessionInfo implements java.io.Serializable {

	private LoginSessionIdInfo id;
	private LoginInfo login;

	public LoginSessionInfo() {
	}

	public LoginSessionInfo(LoginSessionIdInfo id, LoginInfo login) {
		this.id = id;
		this.login = login;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "userName", column = @Column(name = "userName", nullable = false, length = 20)),
			@AttributeOverride(name = "loginStatus", column = @Column(name = "login_status", nullable = false)) })
	public LoginSessionIdInfo getId() {
		return this.id;
	}

	public void setId(LoginSessionIdInfo id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userName", nullable = false, insertable = false, updatable = false)
	public LoginInfo getLogin() {
		return this.login;
	}

	public void setLogin(LoginInfo login) {
		this.login = login;
	}

}

