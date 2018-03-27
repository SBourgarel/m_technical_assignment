package com.sbourgarel.recipesManagement.svc.impl;

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

@SpringBootTest
@ContextConfiguration(classes = Application.class)
@RunWith(SpringRunner.class)
public class CategoriesSvcTest {
	@Autowired
	private CategoriesSvc categoriesSvc;

	@Before
	public void setUp() {
		RecipesContainer.getInstance().getRecipesList().clear();
		// Load the recipes
		RecipeLoader recipeLoader = new RecipeLoader();
		recipeLoader.loadRecipes(ApplicationParam.RECIPES_REPOSITORY);
	}

	@Test
	public void testAllCategoriesRetrieved() {
		assertEquals("7 recipes must be retrieved", 7, this.categoriesSvc.getAllCategories().size());
	}
}
