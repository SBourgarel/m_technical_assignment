package com.sbourgarel.recipesManagement.model;

import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Amount {
	String quantity;
	String unit;
	
	@XmlElement(name = "qty")
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
}
