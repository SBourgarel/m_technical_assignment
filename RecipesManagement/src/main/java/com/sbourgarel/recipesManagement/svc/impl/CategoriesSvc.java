package com.sbourgarel.recipesManagement.svc.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sbourgarel.recipesManagement.model.Recipe;
import com.sbourgarel.recipesManagement.svc.RecipesContainer;

@Service
public class CategoriesSvc {
	public List<String> getAllCategories(){
		List<String> categoriesList = null;
		
		for(Recipe recipe : RecipesContainer.getInstance().getRecipesList()) {
			for(String category : recipe.getHead().getCategories().getCat()) {
				if (categoriesList == null) {
					categoriesList = new ArrayList<>();
				}
				if(!categoriesList.contains(category)){
					categoriesList.add(category);
				}
			}
		}
		return categoriesList;
	}
}
