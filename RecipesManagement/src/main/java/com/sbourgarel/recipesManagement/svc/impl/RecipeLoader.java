package com.sbourgarel.recipesManagement.svc.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.sbourgarel.recipesManagement.model.Recipeml;
import com.sbourgarel.recipesManagement.svc.RecipesContainer;

public class RecipeLoader {

	/**
	 * Load all the Recipemls contained inside a repository. The Recipemls found
	 * are saved in RecipeContainer singleton
	 * 
	 * @param repository
	 *            the repository where to look for Recipemls
	 */
	public void loadRecipes(String repository) {
		List<File> fileList = this.getXmlFiles(repository);

		// If there are no files loaded, no need to go further, exit
		if (fileList == null || fileList.size() == 0) {
			return;
		}

		List<Recipeml> recipes = this.getRecipesFromFileList(fileList);

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
	 * Get all the Recipemls contained in a list of xml files
	 * 
	 * @param files
	 *            List of files containing Recipeml objects
	 * @return the list of Recipemls object retrieved
	 */
	private List<Recipeml> getRecipesFromFileList(List<File> files) {
		List<Recipeml> recipes = new ArrayList<>();
		Recipeml recipe;

		for (File file : files) {
			recipe = this.getRecipeFromXmlFile(file);
			if (recipe != null) {
				recipes.add(recipe);
			}
		}
		return recipes;
	}

	/**
	 * Parse a xml file and extract the Recipeml contained inside
	 * 
	 * @param file
	 *            the xml file containing the Recipeml
	 * @return the Recipeml retrieved
	 */
	private Recipeml getRecipeFromXmlFile(File file) {
		Recipeml recipeml = null;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Recipeml.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			recipeml = (Recipeml) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return recipeml;
	}

	/**
	 * Save the list of Recipemls in parameter, to the recipesContainer
	 * 
	 * @param recipes
	 *            List of the Recipemls to be saved
	 */
	private void saveRecipes(List<Recipeml> recipes) {
		for (Recipeml recipe : recipes) {
			RecipesContainer.getInstance().getRecipesList().add(recipe);
		}
	}
}
