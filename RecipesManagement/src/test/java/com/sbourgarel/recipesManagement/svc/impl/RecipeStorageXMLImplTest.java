package com.sbourgarel.recipesManagement.svc.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.sbourgarel.recipesManagement.Application;
import com.sbourgarel.recipesManagement.ApplicationParam;
import com.sbourgarel.recipesManagement.model.Head;
import com.sbourgarel.recipesManagement.model.Recipe;
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
		assertEquals("1 recipe must be retrieved", 1,
				this.recipeStorageXMLImpl.getAllRecipesForCategory("dish").size());
	}

	@Test
	public void testNullReturnedIfNoRecipeFoundForCategory() {
		assertEquals("The method should return  an empty list", 0,
				this.recipeStorageXMLImpl.getAllRecipesForCategory("bla").size());
	}

	@Test
	public void testAllRecipesBySearchOptionsRetrieved() {
		assertEquals("2 recipes must be retrieved", 2,
				this.recipeStorageXMLImpl.getAllRecipesForSearchOptions("dish").size());
	}

	@Test
	public void testNullReturnedIfNoRecipeFoundForSearchOptions() {
		assertEquals("The method should return an empty list", 0,
				this.recipeStorageXMLImpl.getAllRecipesForSearchOptions("bla").size());
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

		this.recipeStorageXMLImpl.addRecipe(recipe);
		assertEquals("There are now 4 recipes", 4, RecipesContainer.getInstance().getRecipesList().size());
	}

	@Test
	public void testRecipeInvalidIfNoTitle() {
		Recipe recipe = RecipesContainer.getInstance().getRecipesList().get(0);
		recipe.getHead().setTitle(null);
		;
		assertFalse(this.recipeStorageXMLImpl.isRecipeValid(recipe));
	}

	@Test
	public void testRecipeInvalidIfNoYeld() {
		Recipe recipe = RecipesContainer.getInstance().getRecipesList().get(0);
		recipe.getHead().setYield(0);
		assertFalse(this.recipeStorageXMLImpl.isRecipeValid(recipe));
	}

	@Test
	public void testRecipeInvalidIfNoIngredientsAndNoIng_div() {
		Recipe recipe = RecipesContainer.getInstance().getRecipesList().get(0);
		recipe.getIngredients().setIngredients(null);
		recipe.getIngredients().setIngredients_division(null);
		assertFalse(this.recipeStorageXMLImpl.isRecipeValid(recipe));
	}

	@Test
	public void testRecipeInvalidIfIngredientsAndIng_div() {
		Recipe recipe = RecipesContainer.getInstance().getRecipesList().get(0);
		recipe.getIngredients().setIngredients(new ArrayList<>());
		recipe.getIngredients().setIngredients_division(new ArrayList<>());
		assertFalse(this.recipeStorageXMLImpl.isRecipeValid(recipe));
	}

	@Test
	public void testRecipeInvalidIfNoStep() {
		Recipe recipe = RecipesContainer.getInstance().getRecipesList().get(0);
		recipe.getDirections().setStep(null);
		assertFalse(this.recipeStorageXMLImpl.isRecipeValid(recipe));
	}

	@Test
	public void testRecipeValid() {
		Recipe recipe = RecipesContainer.getInstance().getRecipesList().get(0);
		assertTrue(this.recipeStorageXMLImpl.isRecipeValid(recipe));
	}

	@Test
	public void testIsRecipeUnique() {
		Recipe recipe = RecipesContainer.getInstance().getRecipesList().get(0);
		assertFalse(this.recipeStorageXMLImpl.isRecipeUnique(recipe));

		recipe = new Recipe();
		Head head = new Head();
		head.setTitle(RecipesContainer.getInstance().getRecipesList().get(0).getHead().getTitle() + " 2.0");
		head.setYield(RecipesContainer.getInstance().getRecipesList().get(0).getHead().getYield());
		head.setCategories(RecipesContainer.getInstance().getRecipesList().get(0).getHead().getCategories());

		recipe.setHead(head);
		recipe.setIngredients(RecipesContainer.getInstance().getRecipesList().get(0).getIngredients());
		recipe.setDirections(RecipesContainer.getInstance().getRecipesList().get(0).getDirections());

		assertTrue(this.recipeStorageXMLImpl.isRecipeUnique(recipe));

	}
}
