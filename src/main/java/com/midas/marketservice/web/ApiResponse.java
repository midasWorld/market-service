package com.midas.marketservice.web;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ApiResponse<T> {

	private final boolean success;
	private final T response;
	private final ApiError error;

	public ApiResponse(boolean success, T response, ApiError error) {
		this.success = success;
		this.response = response;
		this.error = error;
	}

	public static <T> ApiResponse<T> OK(T response) {
		return new ApiResponse<>(true, response, null);
	}

	public static ApiResponse<?> ERROR(LocalDateTime timestamp, String message) {
		return new ApiResponse<>(false, null, new ApiError(timestamp, message));
	}

	public static ApiResponse<?> ERROR(LocalDateTime timestamp, Throwable throwable) {
		return new ApiResponse<>(false, null, new ApiError(timestamp, throwable));
	}
}
