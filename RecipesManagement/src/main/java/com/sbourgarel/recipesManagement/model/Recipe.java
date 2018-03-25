package com.sbourgarel.recipesManagement.model;

import lombok.Data;

@Data
public class Recipe {
	private Head head;
	private Ingredients ingredients;
	private Directions directions;
}
