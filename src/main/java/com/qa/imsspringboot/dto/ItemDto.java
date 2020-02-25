package com.qa.imsspringboot.dto;

import java.math.BigDecimal;

import com.qa.imsspringboot.persistence.model.Item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@AllArgsConstructor
@Getter @Setter
public class ItemDto {
	
	Long id;
	String name;
	BigDecimal price;
	
	public ItemDto(Item item) {
		this.id = item.getId();
		this.name = item.getName();
		this.price = item.getPrice();
	}
	
}
