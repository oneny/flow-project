package com.flow.extension.domain.extension.service;

import static com.flow.extension.domain.extension.fixture.ExtensionFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flow.extension.domain.extension.dto.request.ExtensionRequest;
import com.flow.extension.domain.extension.exception.DuplicateExtensionException;
import com.flow.extension.domain.extension.repository.ExtensionRepository;

@ExtendWith(MockitoExtension.class)
class ExtensionServiceTest {

	ExtensionService extensionService;
	@Mock
	ExtensionRepository extensionRepository;

	@BeforeEach
	void setUp() {
		extensionService = new ExtensionService(extensionRepository);
	}

	@Test
	@DisplayName("차단 확장자 중복 테스트")
	void duplicateExtension() {
		// given
		given(extensionRepository.findByName(any()))
			.willReturn(Optional.of(DEFAULT_EXTENSION));

		// when, then
		assertThatThrownBy(() -> extensionService.insertExtension(DEFAULT_EXTENSION_REQUEST))
			.isInstanceOf(DuplicateExtensionException.class);
	}

	@ParameterizedTest(name = "차단 확장자 유효성 검사")
	@ValueSource(strings = {"abcdefsderaasdersgdio", "", "한글"})
	void validateExtension(String input) {

		assertThatThrownBy(() -> extensionService.insertExtension(ExtensionRequest.builder()
			.name(input)
			.build()))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("차단 확장자 성공 테스트")
	void successInsertExtension() {
		// given
		given(extensionRepository.findByName(any()))
			.willReturn(Optional.empty());

		// when
		extensionService.insertExtension(DEFAULT_EXTENSION_REQUEST);

		// then
		then(extensionRepository)
			.should()
			.insertExtension(any());
	}
}