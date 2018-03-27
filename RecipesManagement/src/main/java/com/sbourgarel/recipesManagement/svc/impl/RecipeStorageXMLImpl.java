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
	public List<Recipe> getAllRecipesForCategory(String category) {
		List<Recipe> recipesList = new ArrayList<>();;

		for (Recipe recipe : RecipesContainer.getInstance().getRecipesList()) {
			if (this.searchIfListContainsSearchOption(category, recipe.getHead().getCategories().getCat())) {
				recipesList.add(recipe);
			}
		}

		return recipesList;
	}

	@Override
	public List<Recipe> getAllRecipesForSearchOptions(String searchOptions) {
		List<Recipe> recipesList = new ArrayList<>();

		for (Recipe recipe : RecipesContainer.getInstance().getRecipesList()) {
			if (this.searchIfSearchOptionsInRecipe(searchOptions, recipe)) {
				recipesList.add(recipe);
			}
		}

		return recipesList;
	}

	/**
	 * Search for the searchOptions parameters inside some fields of a recipe
	 * object. Fields scanned : Recipe.Head.title, Recipe.Head.categories. The
	 * search is not case sensitive and is a 'contain'.
	 * 
	 * @param searchOptions
	 *            the string to look for
	 * @param recipe
	 *            the object in which one to look
	 * @return true : searchOptions found ; false : searchOptions not found
	 */
	private boolean searchIfSearchOptionsInRecipe(String searchOptions, Recipe recipe) {
		if (StringUtils.containsIgnoreCase(recipe.getHead().getTitle(), searchOptions)) {
			return true;
		}

		if (this.searchIfListContainsSearchOption(searchOptions, recipe.getHead().getCategories().getCat())) {
			return true;
		}

		return false;
	}

	/**
	 * Search if a string is contained in a list of String. The search is not
	 * case sensitive and is a 'contain'.
	 * 
	 * @param searchOptions
	 *            the string to look for
	 * @param list
	 *            the list to analyse
	 * @return
	 */
	private boolean searchIfListContainsSearchOption(String searchOptions, List<String> list) {
		for (String entry : list) {
			if (StringUtils.containsIgnoreCase(entry, searchOptions)) {
				return true;
			}
		}

		return false;
	}
}
