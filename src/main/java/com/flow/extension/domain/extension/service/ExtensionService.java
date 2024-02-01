package com.flow.extension.domain.extension.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flow.extension.domain.extension.dto.request.ExtensionRequest;
import com.flow.extension.domain.extension.entity.Extension;
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
		extensionRepository.insertExtension(extensionRequest.toEntity());
	}

	@Transactional
	public void deleteExtension(ExtensionRequest extensionRequest) {
		extensionRepository.deleteExtension(extensionRequest.toEntity());
	}
}
