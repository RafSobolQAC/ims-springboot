package com.qa.imsspringboot.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.qa.imsspringboot.dto.CustomerDto;
import com.qa.imsspringboot.service.CustomerService;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {
	
	@Mock
	private CustomerService customerService;
	
	@InjectMocks
	private CustomerController customerController;
	
	private List<CustomerDto> customerDtos;
	private CustomerDto customerDto;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		customerDto = new CustomerDto(2L, "A TestName");
	}
	
	@Test
	public void getCustomersTest() {
		customerDtos = new ArrayList<>();
		customerDtos.add(new CustomerDto(1L, "Name"));
		customerDtos.add(new CustomerDto(3L, "OtherName"));
		Mockito.when(customerService.getCustomers()).thenReturn(customerDtos);

		assertEquals(2, customerController.getCustomers().size());
	}
	
	@Test
	public void getCustomerTest() throws NotFoundException {
		Mockito.when(customerService.getCustomer(Mockito.anyLong())).thenReturn(customerDto);
		assertEquals(customerDto, customerController.getCustomer(2L));
	}
	
	@Test
	public void createCustomerTest() {
		Mockito.when(customerService.createCustomer(Mockito.any(CustomerDto.class))).thenReturn(customerDto);
		assertEquals(customerDto, customerController.createCustomer(customerDto));
	}
	
	@Test
	public void updateCustomerTest() throws NotFoundException {
		Mockito.when(customerService.updateCustomer(Mockito.any(CustomerDto.class))).thenReturn(customerDto);
		assertEquals(customerDto, customerController.updateCustomer(customerDto));
	}
	
	@Test
	public void deleteCustomerTest() throws NotFoundException {
		Mockito.when(customerService.deleteCustomer(Mockito.anyLong())).thenReturn(customerDto);
		assertEquals(customerDto, customerController.deleteCustomer(1L));
	}
}
