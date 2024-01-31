package com.flow.extension.domain.extension.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExtensionController {

	@GetMapping("/")
	public String getIndexPage() {
		return "index";
	}
}
