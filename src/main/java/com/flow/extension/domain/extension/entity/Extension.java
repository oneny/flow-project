package com.flow.extension.domain.extension.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode(of = "id")
public class Extension {

	private Long id;
	private String name;
}
