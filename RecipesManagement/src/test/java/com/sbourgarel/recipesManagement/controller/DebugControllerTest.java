package com.sbourgarel.recipesManagement.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

public class DebugControllerTest {

	private DebugController debugController;

	@Before
	public void setUp() {
		this.debugController = new DebugController();
	}

	@Test
	public void testGetDebugInfo() {
		assertEquals("Application status and version must be returned", "Application is up, deployed version : null",
				this.debugController.getDebugInfo().getBody());
		assertEquals("HttpStatus must be OK", HttpStatus.OK, this.debugController.getDebugInfo().getStatusCode());
	}
}
