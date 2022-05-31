package com.midas.marketservice.web.user;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class LoginRequest {

	private final String principal;
	private final String credentials;

	public LoginRequest(String principal, String credentials) {
		this.principal = principal;
		this.credentials = credentials;
	}
}
