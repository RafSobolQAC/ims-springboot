package com.qa.imsspringboot.controller;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
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
import com.qa.imsspringboot.dto.ItemDto;
import com.qa.imsspringboot.service.ItemService;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {
	
	@Mock
	private ItemService itemService;
	
	@InjectMocks
	private ItemController itemController;
	
	private List<ItemDto> itemDtos;
	private ItemDto itemDto;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		itemDto = new ItemDto(2L, "A Name", BigDecimal.valueOf(5.00));
	}
	
	@Test
	public void getCustomersTest() {
		itemDtos = new ArrayList<>();
		itemDtos.add(new ItemDto(1L, "Name",BigDecimal.valueOf(2)));
		itemDtos.add(new ItemDto(3L, "OtherName",BigDecimal.valueOf(4)));
		Mockito.when(itemService.getItems()).thenReturn(itemDtos);

		assertEquals(2, itemController.getItems().size());
	}
	
	@Test
	public void getCustomerTest() throws NotFoundException {
		Mockito.when(itemService.getItem(Mockito.anyLong())).thenReturn(itemDto);
		assertEquals(itemDto, itemController.getItem(2L));
	}
	
	@Test
	public void createCustomerTest() {
		Mockito.when(itemService.createItem(Mockito.any(ItemDto.class))).thenReturn(itemDto);
		assertEquals(itemDto, itemController.createItem(itemDto));
	}
	
	@Test
	public void updateCustomerTest() throws NotFoundException {
		Mockito.when(itemService.updateItem(Mockito.any(ItemDto.class))).thenReturn(itemDto);
		assertEquals(itemDto, itemController.updateItem(itemDto));
	}
	
	@Test
	public void deleteCustomerTest() throws NotFoundException {
		Mockito.when(itemService.deleteItem(Mockito.anyLong())).thenReturn(itemDto);
		assertEquals(itemDto, itemController.deleteItem(1L));
	}
}
