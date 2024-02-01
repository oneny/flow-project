package com.flow.extension.domain.extension.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.flow.extension.domain.extension.dto.request.ExtensionRequest;
import com.flow.extension.domain.extension.entity.Extension;
import com.flow.extension.domain.extension.exception.DuplicateExtensionException;
import com.flow.extension.domain.extension.repository.ExtensionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExtensionService {

	private final ExtensionRepository extensionRepository;

	@Transactional(readOnly = true)
	public List<Extension> findAll() {
		return extensionRepository.findAll();
	}

	@Transactional
	public void insertExtension(ExtensionRequest extensionRequest) {
		validateExtensionRequest(extensionRequest);
		Extension extension = extensionRequest.toEntity();
		Optional<Extension> findExtension = extensionRepository.findByName(extension.getName());

		if (findExtension.isPresent()) {
			throw new DuplicateExtensionException("이미 차단 확장자가 존재합니다.");
		}

		extensionRepository.insertExtension(extension);
	}

	@Transactional
	public void deleteExtension(ExtensionRequest extensionRequest) {
		extensionRepository.deleteExtension(extensionRequest.toEntity());
	}

	private void validateExtensionRequest(ExtensionRequest extensionRequest) {
		Assert.hasText(extensionRequest.getName(), "확장자를 입력해야 합니다.");
		Assert.isTrue(Pattern.matches("^[a-zA-Z]{1,20}$", extensionRequest.getName()), "대소문자 1-20글자이어야 합니다.");
	}
}
