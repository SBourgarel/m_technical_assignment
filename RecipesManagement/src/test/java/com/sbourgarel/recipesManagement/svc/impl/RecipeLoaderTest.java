package com.sbourgarel.recipesManagement.svc.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.sbourgarel.recipesManagement.ApplicationParam;
import com.sbourgarel.recipesManagement.svc.RecipesContainer;

public class RecipeLoaderTest {

	private RecipeLoader recipeLoader;

	@Before
	public void setUp() {
		this.recipeLoader = new RecipeLoader();
	}

	@Test
	public void testRecipesLoadedCorrectly() {
		RecipesContainer.getInstance().getRecipesList().clear();
		recipeLoader.loadRecipes(ApplicationParam.RECIPES_REPOSITORY);
		assertEquals("Recipe container should contain 3 recipes", 3,
				RecipesContainer.getInstance().getRecipesList().size());
		assertEquals("First recipe '30 Minute Chili' loaded", "30 Minute Chili",
				RecipesContainer.getInstance().getRecipesList().get(0).getHead().getTitle());
		assertEquals("Second recipe 'Amaretto Cake' loaded", "Amaretto Cake",
				RecipesContainer.getInstance().getRecipesList().get(1).getHead().getTitle());
		assertEquals("Third recipe 'Another Zucchini Dish' loaded", "Another Zucchini Dish",
				RecipesContainer.getInstance().getRecipesList().get(2).getHead().getTitle());
	}

	@Test
	public void testNoFilesRetrievedIfWrongRepository() {
		RecipesContainer.getInstance().getRecipesList().clear();
		recipeLoader.loadRecipes(null);
		assertEquals("No recipe retrieved", 0, RecipesContainer.getInstance().getRecipesList().size());
	}

	@Test
	public void testOnlyXmlFilesRetrieved() {
		RecipesContainer.getInstance().getRecipesList().clear();
		recipeLoader.loadRecipes(System.getProperty("user.dir") + "/target");
		assertEquals("No recipe retrieved as there are no xml files on application repository", 0, RecipesContainer.getInstance().getRecipesList().size());
	}

}
