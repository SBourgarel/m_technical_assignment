package com.sbourgarel.recipesManagement.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Ingredients {
	List<Ingredients_division> ingredients_division;
	List<Ingredient> ingredients;

	@XmlElement(name = "ing-div")
	@JsonProperty("ing-div")
	public void setIngredients_division(List<Ingredients_division> ingredients_division) {
		this.ingredients_division = ingredients_division;
	}

	@XmlElement(name = "ing")
	@JsonProperty("ing")
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
}
