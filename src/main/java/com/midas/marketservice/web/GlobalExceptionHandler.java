package com.midas.marketservice.web;

import static com.midas.marketservice.web.ApiResponse.*;
import static java.time.LocalDateTime.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	private ResponseEntity<ApiResponse<?>> newResponse(Throwable throwable, HttpStatus status) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/json");
		return new ResponseEntity<>(ERROR(now(), throwable), headers, status);
	}

	@ExceptionHandler({
		IllegalArgumentException.class,
		MethodArgumentNotValidException.class,
		MultipartException.class
	})
	public ResponseEntity<ApiResponse<?>> handleBadRequestException(Exception e) {
		log.info("Bad request exception occurred: {}", e.getMessage(), e);
		return newResponse(e, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({Exception.class, RuntimeException.class})
	public ResponseEntity<ApiResponse<?>> handleAllException(Exception e) {
		log.error("Unexpected exception occurred: {}", e.getMessage(), e);
		return newResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
