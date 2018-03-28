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
	List<Recipe> getAllRecipes();

	/**
	 * Get a list of recipes which category contain the matching "category"
	 * parameter
	 * 
	 * @param categorie
	 * @return
	 */
	List<Recipe> getAllRecipesForCategory(String category);

	/**
	 * Get a list of recipes matching the "searchOptions" parameter
	 * 
	 * @param searchOptions
	 * @return
	 */
	List<Recipe> getAllRecipesForSearchOptions(String searchOptions);

	/**
	 * Add a new recipe to the storage
	 * 
	 * @param recipe
	 *            the object to be added
	 */
	void addRecipe(Recipe recipe);

	/**
	 * Check whether or not the recipe is ready to be inserted in the storage
	 * 
	 * @param recipe
	 *            the object to be checked
	 * @return true : object correct ; false : object incorrect
	 */
	boolean isRecipeOk(Recipe recipe);

	/**
	 * Check whether or not the recipe object in parameter contains correct data.
	 * 
	 * @param recipe
	 *            the object to be checked
	 * @return true : object correct ; false : object incorrect
	 */
	boolean isRecipeValid(Recipe recipe);

	/**
	 * Check whether or not the recipe is unique.
	 * 
	 * @param recipe
	 *            the object to be checked
	 * @return true : the recipe is unique ; false : there is already a similar
	 *         recipe
	 */
	boolean isRecipeUnique(Recipe recipe);
}
