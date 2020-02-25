package com.qa.imsspringboot.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.imsspringboot.persistence.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

	public Item findItemByName(String name);
	
	
}
