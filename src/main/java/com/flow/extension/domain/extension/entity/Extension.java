package com.flow.extension.domain.extension.entity;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode(of = "id")
public class Extension {

	private Long id;
	private String name;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
