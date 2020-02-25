package com.qa.imsspringboot.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.imsspringboot.persistence.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	public Customer findCustomerByName(String text);

}
