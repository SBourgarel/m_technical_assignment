package com.sbourgarel.recipesManagement.svc.pub;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sbourgarel.recipesManagement.model.Recipe;

@Service
public interface RecipesStorageSvc {
	/**
	 * Get a list of all the recipes stored in memory
	 * 
	 * @return all the recipes
	 */
	List<Recipe>  getAllRecipes();
	
	/**
	 * Get a list of recipes which category contain the matching "category" parameter
	 * 
	 * @param categorie
	 * @return
	 */
	List<Recipe>  getAllRecipesForCategory(String category);
	
	/**
	 * Get a list of recipes matching the "searchOptions" parameter
	 * 
	 * @param searchOptions
	 * @return
	 */
	List<Recipe>  getAllRecipesForSearchOptions(String searchOptions);
}
