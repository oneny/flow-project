package com.flow.extension.domain.extension.dto.request;

import com.flow.extension.domain.extension.entity.Extension;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExtensionRequest {

	private String name;

	public Extension toEntity() {
		return Extension.builder()
			.name(name)
			.build();
	}
}
