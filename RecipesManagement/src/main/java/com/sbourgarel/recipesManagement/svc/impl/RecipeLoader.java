package com.sbourgarel.recipesManagement.svc.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.sbourgarel.recipesManagement.model.Recipe;
import com.sbourgarel.recipesManagement.model.Recipeml;
import com.sbourgarel.recipesManagement.svc.RecipesContainer;

public class RecipeLoader {

	/**
	 * Load all the Recipes contained inside a repository. The Recipes found
	 * are saved in RecipeContainer singleton
	 * 
	 * @param repository
	 *            the repository where to look for Recipes
	 */
	public void loadRecipes(String repository) {
		List<File> fileList = this.getXmlFiles(repository);

		// If there are no files loaded, no need to go further, exit
		if (fileList == null || fileList.size() == 0) {
			return;
		}

		List<Recipe> recipes = this.getRecipesFromFileList(fileList);

		this.saveRecipes(recipes);
	}

	/**
	 * Get all the xml files inside a given repository
	 * 
	 * @param repository
	 *            the repository to look inside
	 * @return the list of xml files found
	 */
	private List<File> getXmlFiles(String repository) {
		if (repository == null || repository.equals("")) {
			return null;
		}

		List<File> fileList = Arrays.asList(new File(repository).listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
		        return name.matches("(.*).xml");
			}
		}));
		
		return fileList;
	}

	/**
	 * Get all the Recipes contained in a list of xml files
	 * It is assumed the 'files' parameter is not null.
	 * 
	 * @param files
	 *            List of files containing Recipe objects
	 * @return the list of Recipes object retrieved
	 */
	private List<Recipe> getRecipesFromFileList(List<File> files) {
		List<Recipe> recipes = new ArrayList<>();
		Recipe recipe;

		for (File file : files) {
			recipe = this.getRecipeFromXmlFile(file);
			if (recipe != null) {
				recipes.add(recipe);
			}
		}
		return recipes;
	}

	/**
	 * Parse a xml file and extract the Recipe contained inside.
	 * It is assumed the 'file' parameter is not null.
	 * 
	 * @param file
	 *            the xml file containing the Recipe
	 * @return the Recipe retrieved
	 */
	private Recipe getRecipeFromXmlFile(File file) {
		Recipeml recipeml = null;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Recipeml.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			recipeml = (Recipeml) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return recipeml.getRecipe();
	}

	/**
	 * Save the list of Recipes in parameter, to the recipesContainer
	 * It is assumed the 'recipes' parameter is not null.
	 * 
	 * @param recipes
	 *            List of the Recipes to be saved
	 */
	private void saveRecipes(List<Recipe> recipes) {
		for (Recipe recipe : recipes) {
			RecipesContainer.getInstance().getRecipesList().add(recipe);
		}
	}
}
