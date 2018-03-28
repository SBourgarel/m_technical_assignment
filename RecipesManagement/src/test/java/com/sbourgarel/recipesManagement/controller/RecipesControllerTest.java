package com.sbourgarel.recipesManagement.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.sbourgarel.recipesManagement.Application;
import com.sbourgarel.recipesManagement.ApplicationParam;
import com.sbourgarel.recipesManagement.model.Head;
import com.sbourgarel.recipesManagement.model.Recipe;
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
	public void testGetRecipesResponses() {
		assertEquals("3 recipes must be retrieved", 3, this.recipesController.getRecipes(null, null).getBody().size());
		assertEquals("1 recipe must be retrieved", 1, this.recipesController.getRecipes("dish", null).getBody().size());
		assertEquals("2 recipes must be retrieved", 2,
				this.recipesController.getRecipes(null, "dish").getBody().size());

		assertEquals("If a category with no matches is set, empty list is returned", 0,
				this.recipesController.getRecipes("bla", null).getBody().size());
		assertEquals("If a search-options with no matches is set, empty list is returned", 0,
				this.recipesController.getRecipes(null, "bla").getBody().size());
		assertEquals("HttpStatus must be Bad_Request : only one parameter can be set at a time", HttpStatus.BAD_REQUEST,
				this.recipesController.getRecipes("dish", "butter").getStatusCode());
	}

	@Test
	public void testCannotAddExistingRecipe() {
		Recipe recipe = RecipesContainer.getInstance().getRecipesList().get(0);

		assertEquals("Error message returned : It is not possible to add an existing reicpe (name already existing)",
				"Recipe not added. Check content or if recipe already exists.",
				this.recipesController.addRecipe(recipe).getBody());
		assertEquals(
				"HttpStatus must be Bad_Request : It is not possible to add an existing reicpe (name already existing)",
				HttpStatus.BAD_REQUEST, this.recipesController.addRecipe(recipe).getStatusCode());
	}

	@Test
	public void testCanAddANewRecipe() {
		Head head = new Head();
		head.setTitle(RecipesContainer.getInstance().getRecipesList().get(0).getHead().getTitle() + " 2.0");
		head.setYield(RecipesContainer.getInstance().getRecipesList().get(0).getHead().getYield());
		head.setCategories(RecipesContainer.getInstance().getRecipesList().get(0).getHead().getCategories());

		Recipe recipe = new Recipe();
		recipe.setHead(head);
		recipe.setIngredients(RecipesContainer.getInstance().getRecipesList().get(0).getIngredients());
		recipe.setDirections(RecipesContainer.getInstance().getRecipesList().get(0).getDirections());

		assertEquals("Recipe added message", "Recipe added", this.recipesController.addRecipe(recipe).getBody());

		RecipesContainer.getInstance().getRecipesList().clear();
		assertEquals("HttpStatus must be OK", HttpStatus.OK, this.recipesController.addRecipe(recipe).getStatusCode());
	}
}
