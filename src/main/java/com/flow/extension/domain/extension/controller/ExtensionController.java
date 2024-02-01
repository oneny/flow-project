package com.flow.extension.domain.extension.controller;

import static com.flow.extension.global.config.ApiResponse.Status.*;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.flow.extension.domain.extension.dto.request.ExtensionRequest;
import com.flow.extension.domain.extension.entity.Extension;
import com.flow.extension.domain.extension.service.ExtensionService;
import com.flow.extension.global.config.ApiResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ExtensionController {

	private final ExtensionService extensionService;

	@GetMapping("/")
	public String getIndexPage() {
		return "index";
	}

	@ResponseBody
	@GetMapping("/extensions")
	public ApiResponse<List<Extension>> selectAllExtensions() {
		return ApiResponse.<List<Extension>>builder()
			.status(SUCCESS)
			.result(extensionService.findAll())
			.build();
	}

	@ResponseBody
	@PostMapping("/extensions")
	public ApiResponse insertExtension(@RequestBody ExtensionRequest extensionRequest) {
		extensionService.insertExtension(extensionRequest);

		return ApiResponse.builder()
			.message("성공적으로 추가되었습니다.")
			.status(SUCCESS)
			.build();
	}

	@ResponseBody
	@DeleteMapping("/extensions")
	public ApiResponse deleteExtension(@RequestBody ExtensionRequest extensionRequest) {
		extensionService.deleteExtension(extensionRequest);

		return ApiResponse.builder()
			.message("성공적으로 삭제되었습니다.")
			.status(SUCCESS)
			.build();
	}
}
