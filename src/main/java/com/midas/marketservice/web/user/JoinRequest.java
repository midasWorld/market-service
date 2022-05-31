package com.midas.marketservice.web.user;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class JoinRequest {

	private final String email;
	private final String password;
	private final String name;

	public JoinRequest(String email, String password, String name) {
		this.email = email;
		this.password = password;
		this.name = name;
	}
}
