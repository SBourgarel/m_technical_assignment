package com.sbourgarel.recipesManagement.model;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
public class Recipeml {
	Recipe recipe;	
}
