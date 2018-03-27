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
public class RecipesCategoriesControllerTest {

	@Autowired
	private RecipesCategoriesController recipesCategoriesController;

	@Before
	public void setUp() {
		RecipesContainer.getInstance().getRecipesList().clear();
		// Load the recipes
		RecipeLoader recipeLoader = new RecipeLoader();
		recipeLoader.loadRecipes(ApplicationParam.RECIPES_REPOSITORY);
	}

	@Test
	public void testCorrectAmountOfCategoriesReturned() {
		assertEquals("7 recipes must be retrieved", 7, this.recipesCategoriesController.getCategoies().size());
	}
}
