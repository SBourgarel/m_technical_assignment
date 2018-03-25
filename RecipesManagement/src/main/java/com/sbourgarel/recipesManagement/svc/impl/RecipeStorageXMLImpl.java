package com.sbourgarel.recipesManagement.svc.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import com.sbourgarel.recipesManagement.model.Recipe;
import com.sbourgarel.recipesManagement.svc.RecipesContainer;
import com.sbourgarel.recipesManagement.svc.pub.RecipesStorageSvc;

@Service
public class RecipeStorageXMLImpl implements RecipesStorageSvc {
	@Override
	public List<Recipe> getAllRecipes() {
		return RecipesContainer.getInstance().getRecipesList();
	}

	@Override
	public List<Recipe> getAllRecipesForCategorie(String categorie) {
		List<Recipe> recipesList = null;
		
		for(Recipe recipe : RecipesContainer.getInstance().getRecipesList()) {
			if(this.searchIfListContainsSearchOption(categorie, recipe.getHead().getCategories().getCat())) {
				if(recipesList == null) {
					recipesList = new ArrayList<>();
				}
				recipesList.add(recipe);
			}
		}
		
		return recipesList;
	}

	@Override
	public List<Recipe> getAllRecipesForSearchOptions(String searchOptions) {
		List<Recipe> recipesList = null;
		
		for(Recipe recipe : RecipesContainer.getInstance().getRecipesList()) {
			if(this.searchIfSearchOptionsInObject(searchOptions, recipe)) {
				if(recipesList == null) {
					recipesList = new ArrayList<>();
				}
				recipesList.add(recipe);
			}
		}
		
		return recipesList;
	}
	
	private boolean searchIfSearchOptionsInObject(String searchOptions, Recipe recipe) {
		if(StringUtils.containsIgnoreCase(recipe.getHead().getTitle(), searchOptions)) {
			return true;
		}
		
		if(this.searchIfListContainsSearchOption(searchOptions, recipe.getHead().getCategories().getCat())) {
			return true;
		}
		
		return false;
	}
	
	private boolean searchIfListContainsSearchOption(String searchOptions, List<String> list) {
		for(String entry : list) {
			if(StringUtils.containsIgnoreCase(entry,searchOptions)) {
				return true;
			}
		}
		
		return false;
	}
}
