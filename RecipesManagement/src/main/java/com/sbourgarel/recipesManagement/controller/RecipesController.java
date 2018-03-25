package com.sbourgarel.recipesManagement.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@GetMapping(produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getRecipes(
			@RequestParam(value = "categorie", required = false) final String categorie,
			@RequestParam(value = "search-options", required = false) final String searchOptions) {
		List<Recipe> recipes = null;
		
        // If there are more than 1 parameters not null, error, get out and send bad_request
        if(this.countHowManyEntriesNotNull(categorie, searchOptions) > 1) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
				
		if(categorie != null) {
			recipes = this.recipeStorage.getAllRecipesForCategorie(categorie);
		} else if(searchOptions != null){
			recipes = this.recipeStorage.getAllRecipesForSearchOptions(searchOptions);
		} else {
			recipes = this.recipeStorage.getAllRecipes();			
		}
		
		return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
	}

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
