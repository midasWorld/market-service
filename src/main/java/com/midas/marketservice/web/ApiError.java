package com.midas.marketservice.web;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ApiError {

	private final LocalDateTime timestamp;
	private final String message;

	public ApiError(LocalDateTime timestamp, String message) {
		this.timestamp = timestamp;
		this.message = message;
	}

	public ApiError(LocalDateTime timestamp, Throwable throwable) {
		this(timestamp, throwable.getMessage());
	}
}
