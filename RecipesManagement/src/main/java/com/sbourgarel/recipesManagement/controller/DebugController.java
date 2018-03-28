package com.sbourgarel.recipesManagement.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebugController {
	@Value("${application.version}")
	String implementationVersion;

	/**
	 * @return the deployed version
	 */
	@GetMapping(produces = MediaType.TEXT_PLAIN, path = "/debug")
	public ResponseEntity<String> getDebugInfo() {
		return new ResponseEntity<>("Application is up, deployed version : " + implementationVersion, HttpStatus.OK);
	}
}
