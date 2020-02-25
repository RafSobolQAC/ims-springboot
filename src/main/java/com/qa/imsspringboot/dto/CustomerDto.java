package com.qa.imsspringboot.dto;

import com.qa.imsspringboot.persistence.model.Customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@AllArgsConstructor
@Getter @Setter
public class CustomerDto {

	Long id;
	String name;
		
	
	public CustomerDto(Customer customer) {
		this.id = customer.getId();
		this.name = customer.getName();
	}
	
}
