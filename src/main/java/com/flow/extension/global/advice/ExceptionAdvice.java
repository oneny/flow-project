package com.flow.extension.global.advice;

import static com.flow.extension.global.config.ApiResponse.Status.*;
import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flow.extension.domain.extension.exception.DuplicateExtensionException;
import com.flow.extension.global.config.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(IllegalArgumentException.class)
	protected ApiResponse illegalArgumentException(IllegalArgumentException exception) {
		log.info("IllegalArgumentException: {}", exception.getMessage());
		return ApiResponse.builder()
				.status(FAIL)
				.message(exception.getMessage())
				.build();
	}

	@ExceptionHandler(DuplicateExtensionException.class)
	protected ApiResponse duplicateExtensionException(DuplicateExtensionException exception) {
		log.info("DuplicateExtensionException: {}", exception.getMessage());
		return ApiResponse.builder()
			.status(FAIL)
			.message(exception.getMessage())
			.build();
	}
}
