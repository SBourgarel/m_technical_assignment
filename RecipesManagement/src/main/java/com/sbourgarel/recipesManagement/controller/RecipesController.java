package com.sbourgarel.recipesManagement.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sbourgarel.recipesManagement.model.Recipe;
import com.sbourgarel.recipesManagement.svc.pub.RecipesStorageSvc;

@RestController
@RequestMapping(path = "/recipes")
public class RecipesController {

	@Autowired
	private RecipesStorageSvc recipeStorage;
	
	/**
	 * Returns a list of recipes depending on the content of the parameters.
	 * Only one parameter can be set at a time.
	 * Sending no parameters will return all the recipes.
	 * When filling the "searchOptions" parameter, the application will look for all the recipes matching the "searchOptions".
	 * Fields inspected : Recipe.Head.title, Recipe.Head.categories
	 * 
	 * @param category
	 * @param searchOptions
	 * @return
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON)
	public List<Recipe> getRecipes(
			@RequestParam(value = "category", required = false) final String category,
			@RequestParam(value = "search-options", required = false) final String searchOptions) {
		List<Recipe> recipes = null;
		
        // If there are more than 1 parameters not null, error, get out and send bad_request
        if(this.countHowManyEntriesNotNull(category, searchOptions) > 1) {
    		return null;
        }
				
		if(category != null) {
			recipes = this.recipeStorage.getAllRecipesForCategory(category);
		} else if(searchOptions != null){
			recipes = this.recipeStorage.getAllRecipesForSearchOptions(searchOptions);
		} else {
			recipes = this.recipeStorage.getAllRecipes();			
		}
		
		return recipes;
	}

	/**
	 * Returns the numer of entries not empty from a table.
	 * 
	 * @param table the table to inspect
	 * @returnthe number of entries of the table, that are not empty.
	 */
    private int countHowManyEntriesNotNull(String... table) {
        int count = 0;

        for(int i = 0 ; i < table.length ; i++) {
            if(StringUtils.isNotEmpty(table[i])) {
                count++;
            }
        }
        return count;
    }
}
