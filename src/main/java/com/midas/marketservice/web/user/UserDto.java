package com.midas.marketservice.web.user;

import java.time.LocalDateTime;

import com.midas.marketservice.domain.user.User;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class UserDto {

	private final String email;
	private final String name;
	private final LocalDateTime createdAt;

	public UserDto(User user) {
		this.email = user.getEmail();
		this.name = user.getName();
		this.createdAt = user.getCreatedAt();
	}
}
