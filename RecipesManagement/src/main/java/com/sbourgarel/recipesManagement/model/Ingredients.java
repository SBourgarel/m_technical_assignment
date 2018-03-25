package com.sbourgarel.recipesManagement.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Ingredients {
	List<Ingredients_division> ingredients_division;
	List<Ingredient> ingredients;

	@XmlElement(name = "ing-div")
	public void setIngredients_division(List<Ingredients_division> ingredients_division) {
		this.ingredients_division = ingredients_division;
	}

	
	@XmlElement(name = "ing")
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}	
}
