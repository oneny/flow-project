package com.flow.extension.domain.extension.fixture;

import com.flow.extension.domain.extension.dto.request.ExtensionRequest;
import com.flow.extension.domain.extension.entity.Extension;

public class ExtensionFixture {

	public static ExtensionRequest DEFAULT_EXTENSION_REQUEST = ExtensionRequest.builder()
		.name("jpg")
		.build();

	public static Extension DEFAULT_EXTENSION = Extension.builder()
		.id(1L)
		.name("jpg")
		.build();
}
