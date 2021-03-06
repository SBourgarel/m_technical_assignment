package com.sbourgarel.recipesManagement.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Ingredients_division {
	String title;
	List<Ingredient> ingredients;

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement(name = "ing")
	@JsonProperty("ing")
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
}
