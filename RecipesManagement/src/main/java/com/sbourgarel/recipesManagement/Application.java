package com.sbourgarel.recipesManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sbourgarel.recipesManagement.svc.impl.RecipeLoader;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        RecipeLoader recipeLoader = new RecipeLoader();
        recipeLoader.loadRecipes(ApplicationParam.RECIPES_REPOSITORY);
    }
}