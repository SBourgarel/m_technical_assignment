package com.sbourgarel.recipesManagement.svc;

import java.util.ArrayList;
import java.util.List;

import com.sbourgarel.recipesManagement.model.Recipe;

public class RecipesContainer {
	private static RecipesContainer instance = null;
	private List<Recipe> recipesList;

	private RecipesContainer() {
		this.recipesList = new ArrayList<>();
	}

	public static RecipesContainer getInstance() {
		if (instance == null) {
			instance = new RecipesContainer();
		}
		return instance;
	}
	
	public List<Recipe> getRecipesList(){
		return this.recipesList;
	}
}
