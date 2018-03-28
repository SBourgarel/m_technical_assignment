package com.sbourgarel.recipesManagement.model;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Ingredient {
	Amount amount;
	String item;

	@XmlElement(name = "amt")
	@JsonProperty("amt")
	public void setAmount(Amount amount) {
		this.amount = amount;
	}

	public void setItem(String item) {
		this.item = item;
	}

}
