package com.sbourgarel.recipesManagement.svc.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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
		List<Recipe> recipesList = new ArrayList<>();

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

	@Override
	public void addRecipe(Recipe recipe) {
		RecipesContainer.getInstance().getRecipesList().add(recipe);
	}

	@Override
	public boolean isRecipeOk(Recipe recipe) {
		return this.isRecipeValid(recipe) && this.isRecipeUnique(recipe);
	}

	@Override
	public boolean isRecipeValid(Recipe recipe) {
		// Title has to be set
		if (StringUtils.isEmpty(recipe.getHead().getTitle())) {
			return false;
		}
		// Yeld has to be > 0
		if (recipe.getHead().getYield() < 1) {
			return false;
		}
		// Recipe.Ingredients.ing or Recpie.Ingredients.ing-div has to be set,
		// but not at the same time.
		if (!((recipe.getIngredients().getIngredients() == null
				&& recipe.getIngredients().getIngredients_division() != null)

				|| (recipe.getIngredients().getIngredients() != null
						&& recipe.getIngredients().getIngredients_division() == null))) {
			return false;
		}
		// From this point, either Recipe.Ingredients.ing or
		// Recpie.Ingredients.ing-div is not null. But they have to have at
		// least 1 ingredient!
		if ((recipe.getIngredients().getIngredients() != null && recipe.getIngredients().getIngredients().isEmpty())
				|| (recipe.getIngredients().getIngredients_division() != null
						&& recipe.getIngredients().getIngredients_division().isEmpty())) {
			return false;
		}
		// Recipe.Directions.step should not be null or empty
		if (StringUtils.isEmpty(recipe.getDirections().getStep())) {
			return false;
		}

		return true;
	}

	/**
	 * Check whether or not there is already a recipe stored with the same name. The
	 * check is not case sensitive
	 */
	@Override
	public boolean isRecipeUnique(Recipe recipe) {
		for (Recipe recipe_Stored : RecipesContainer.getInstance().getRecipesList()) {
			if (StringUtils.equalsIgnoreCase(recipe.getHead().getTitle(), recipe_Stored.getHead().getTitle())) {
				return false;
			}
		}

		return true;
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
	 * Search if a string is contained in a list of String. The search is not case
	 * sensitive and is a 'contain'.
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
