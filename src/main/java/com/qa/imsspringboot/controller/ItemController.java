package com.qa.imsspringboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qa.imsspringboot.dto.ItemDto;
import com.qa.imsspringboot.service.ItemService;

@RestController
@CrossOrigin("*")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(path="item/", method= {RequestMethod.GET})
	@ResponseStatus(code=HttpStatus.OK)
	public List<ItemDto> getItems(){
		return new ArrayList<ItemDto>(itemService.getItems());
	}
	
	@RequestMapping(path="item/id/{id}", method= {RequestMethod.GET})
	@ResponseStatus(code=HttpStatus.OK)
	public ItemDto getItem(Long id) throws NotFoundException {
		return itemService.getItem(id);
	}

	@RequestMapping(path="item/", method= {RequestMethod.POST})
	@ResponseStatus(code=HttpStatus.CREATED)
	public ItemDto createItem(ItemDto itemDto) {
		return itemService.createItem(itemDto);
	}
	
	@RequestMapping(path="item/", method= {RequestMethod.PUT})
	@ResponseStatus(code=HttpStatus.OK)
	public ItemDto updateItem(ItemDto itemDto) throws NotFoundException {
		return itemService.updateItem(itemDto);
	}
	
	@RequestMapping(path="item/id/{id}", method= {RequestMethod.DELETE})
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public ItemDto deleteItem(Long id) throws NotFoundException {
		return itemService.deleteItem(id);
	}
}
