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
public class RecipeStorageXMLImplTest {

	@Autowired
	private RecipeStorageXMLImpl recipeStorageXMLImpl;

	@Before
	public void setUp() {
		RecipesContainer.getInstance().getRecipesList().clear();
		// Load the recipes
		RecipeLoader recipeLoader = new RecipeLoader();
		recipeLoader.loadRecipes(ApplicationParam.RECIPES_REPOSITORY);
	}

	@Test
	public void testAllRecipesRetrieved() {
		assertEquals("3 recipes must be retrieved", 3, this.recipeStorageXMLImpl.getAllRecipes().size());
	}

	@Test
	public void testAllRecipesByCategoryRetrieved() {
		assertEquals("1 recipe must be retrieved", 1, this.recipeStorageXMLImpl.getAllRecipesForCategory("dish").size());
	}

	@Test
	public void testNullReturnedIfNoRecipeFoundForCategory() {
		assertEquals("The method should return  an empty list", 0, this.recipeStorageXMLImpl.getAllRecipesForCategory("bla").size());
	}

	@Test
	public void testAllRecipesBySearchOptionsRetrieved() {
		assertEquals("2 recipes must be retrieved", 2, this.recipeStorageXMLImpl.getAllRecipesForSearchOptions("dish").size());
	}

	@Test
	public void testNullReturnedIfNoRecipeFoundForSearchOptions() {
		assertEquals("The method should return an empty list", 0, this.recipeStorageXMLImpl.getAllRecipesForSearchOptions("bla").size());
	}

}
