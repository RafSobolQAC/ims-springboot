package com.qa.imsspringboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.qa.imsspringboot.dto.CustomerDto;
import com.qa.imsspringboot.persistence.model.Customer;
import com.qa.imsspringboot.persistence.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * Finds all customers in the database. DTO is used to ensure no actual entity
	 * is returned - first you find entities in the table, then you return the
	 * 'safe' DTOs.
	 * 
	 * @return A list of all CustomerDto objects.
	 */
	public List<CustomerDto> getCustomers() {
		List<Customer> customers = customerRepository.findAll();
		List<CustomerDto> customersDto = new ArrayList<>();
		customers.forEach(customer -> customersDto.add(new CustomerDto(customer)));
		return customersDto;
	}

	public CustomerDto createCustomer(CustomerDto customerDto) {
		customerDto.setId(null);
		Customer customer = new Customer();
		customer.setName(customerDto.getName());
		return new CustomerDto(customerRepository.saveAndFlush(customer));
	}

	public CustomerDto updateCustomer(CustomerDto customerDto) throws NotFoundException {
		Customer customer = customerRepository.findById(customerDto.getId()).orElseThrow(() -> new NotFoundException());
		customer.setName(customerDto.getName());
		customer.setId(customerDto.getId());
		customerRepository.flush();
		return new CustomerDto(customer);
	}

	public CustomerDto getCustomer(Long id) throws NotFoundException {
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException());
		return new CustomerDto(customer);
	}

	public CustomerDto deleteCustomer(Long id) throws NotFoundException {
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException());
		CustomerDto customerDto = new CustomerDto(customer);
		customerRepository.deleteById(id);
		return customerDto;
	}

	public void deleteAll() {
		customerRepository.deleteAll();
	}
}
