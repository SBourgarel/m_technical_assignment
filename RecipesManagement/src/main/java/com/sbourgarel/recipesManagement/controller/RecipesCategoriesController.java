package com.sbourgarel.recipesManagement.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbourgarel.recipesManagement.svc.impl.CategoriesSvc;

@RestController
@RequestMapping(path = "/recipe-categories")
public class RecipesCategoriesController {

	@Autowired
	private CategoriesSvc categoriesSvc;
	
	/**
	 * 
	 * @return a list of String containing all the categories stored in all the recipes.
	 * The list contains no duplicates.
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON)
	public List<String> getCategoies() {
		List<String> categories = null;
						
		categories = this.categoriesSvc.getAllCategories();			
		
		return categories;
	}
}
