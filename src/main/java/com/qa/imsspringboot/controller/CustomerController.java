package com.qa.imsspringboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qa.imsspringboot.dto.CustomerDto;
import com.qa.imsspringboot.service.CustomerService;

@RestController
@CrossOrigin("*")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(path = "customer/", method= {RequestMethod.GET})
	@ResponseStatus(code=HttpStatus.OK)
	public List<CustomerDto> getCustomers() {
		return new ArrayList<CustomerDto>(customerService.getCustomers());
	}
	
	@RequestMapping(path="customer/id/{id}", method= {RequestMethod.GET})
	@ResponseStatus(code=HttpStatus.OK)
	public CustomerDto getCustomer(@PathVariable("id") Long id) throws NotFoundException {
		return customerService.getCustomer(id);
	}
	
	@RequestMapping(path="customer/",method= {RequestMethod.POST})
	@ResponseStatus(code=HttpStatus.CREATED)
	public CustomerDto createCustomer(@RequestBody CustomerDto customerDto) {
		return customerService.createCustomer(customerDto);
	}
	
	@RequestMapping(path="customer/",method= {RequestMethod.PUT})
	@ResponseStatus(code=HttpStatus.OK)
	public CustomerDto updateCustomer(@RequestBody CustomerDto customerDto) throws NotFoundException {
		return customerService.updateCustomer(customerDto);
	}
	@RequestMapping(path="customer/id/{id}", method= {RequestMethod.DELETE})
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public CustomerDto deleteCustomer(@PathVariable("id") Long id) throws NotFoundException {
		return customerService.deleteCustomer(id);
	}
}
