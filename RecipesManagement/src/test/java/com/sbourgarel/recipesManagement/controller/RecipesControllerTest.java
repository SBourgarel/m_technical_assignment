package com.sbourgarel.recipesManagement.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.sbourgarel.recipesManagement.Application;
import com.sbourgarel.recipesManagement.ApplicationParam;
import com.sbourgarel.recipesManagement.svc.RecipesContainer;
import com.sbourgarel.recipesManagement.svc.impl.RecipeLoader;

@SpringBootTest
@ContextConfiguration(classes = Application.class)
@RunWith(SpringRunner.class)
public class RecipesControllerTest {

	@Autowired
	private RecipesController recipesController;

	@Before
	public void setUp() {
		RecipesContainer.getInstance().getRecipesList().clear();
		// Load the recipes
		RecipeLoader recipeLoader = new RecipeLoader();
		recipeLoader.loadRecipes(ApplicationParam.RECIPES_REPOSITORY);
	}

	@Test
	public void testStatusCodeGetRecipesInfo() {
		assertEquals("3 recipes must be retrieved", 3, this.recipesController.getRecipes(null, null).size());
		assertEquals("1 recipe must be retrieved", 1, this.recipesController.getRecipes("dish", null).size());
		assertEquals("2 recipes must be retrieved", 2, this.recipesController.getRecipes(null, "dish").size());

		assertEquals("If a category with no matches is set, empty list is returned", 0,
				this.recipesController.getRecipes("bla", null).size());
		assertEquals("If a search-options with no matches is set, empty list is returned", 0,
				this.recipesController.getRecipes(null, "bla").size());
		assertEquals("Only one parameter can be set at a time", null,
				this.recipesController.getRecipes("dish", "butter"));
	}
}
