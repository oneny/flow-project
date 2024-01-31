package com.flow.extension.global.config;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiResponse<T> {
	private final Status status;
	private final String message;
	private final T result;

	public enum Status {
		SUCCESS, FAIL
	}
}
