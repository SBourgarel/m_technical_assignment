package com.sbourgarel.recipesManagement.controller;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebugController {

	/**
	 * 
	 * @return the deployed version
	 */
	@GetMapping(produces = MediaType.TEXT_PLAIN, path = "/debug")
	public ResponseEntity<String> getDebugInfo() {

		return new ResponseEntity<>("Application is up, deployed version : " + DebugController.getImplementationVersion(), HttpStatus.OK);
	}

	/**
	 * Get the implementation version from the file manifest.mf
	 * @return the implementation version found
	 */
	private static String getImplementationVersion() {
		String implementationVersion = null;
		
		URLClassLoader cl = (URLClassLoader) DebugController.class.getClassLoader();

		try {
			URL url = cl.findResource("META-INF/MANIFEST.MF");
			Manifest manifest = new Manifest(url.openStream());
			Attributes mainAttributes = manifest.getMainAttributes();
			implementationVersion = mainAttributes.getValue("Implementation-Version");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return implementationVersion;
	}
}
