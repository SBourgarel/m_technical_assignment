package com.sbourgarel.recipeManagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebugController {

	@RequestMapping("/")
	public String index() {
		return "Greetings from Simon!";
	}

}
