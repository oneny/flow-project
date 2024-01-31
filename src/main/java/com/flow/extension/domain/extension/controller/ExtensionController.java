package com.flow.extension.domain.extension.controller;

import static com.flow.extension.global.config.ApiResponse.Status.*;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
}
