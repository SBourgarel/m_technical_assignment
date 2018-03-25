package com.sbourgarel.recipesManagement.svc.pub;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sbourgarel.recipesManagement.model.Recipe;

@Service
public interface RecipesStorageSvc {
	List<Recipe>  getAllRecipes();
	
	List<Recipe>  getAllRecipesForCategorie(String categorie);
	
	List<Recipe>  getAllRecipesForSearchOptions(String searchOptions);
}
