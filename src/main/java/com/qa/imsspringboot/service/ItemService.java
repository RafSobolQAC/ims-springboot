package com.qa.imsspringboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.qa.imsspringboot.dto.ItemDto;
import com.qa.imsspringboot.persistence.model.Item;
import com.qa.imsspringboot.persistence.repository.ItemRepository;

@Service
public class ItemService {
	@Autowired
	private ItemRepository itemRepository;

	public List<ItemDto> getItems() {
		List<Item> items = itemRepository.findAll();
		List<ItemDto> itemDtos = new ArrayList<>();
		for (Item item : items) {
			itemDtos.add(new ItemDto(item));
		}
		return itemDtos;
	}

	public ItemDto getItem(Long id) throws NotFoundException {
		Item item = itemRepository.findById(id).orElseThrow(() -> new NotFoundException());
		return new ItemDto(item);
	}

	public ItemDto createItem(ItemDto itemDto) {
		Item item = new Item();
		item.setName(itemDto.getName());
		item.setPrice(itemDto.getPrice());
		itemDto.setId(null);
		return new ItemDto(itemRepository.saveAndFlush(item));
	}

	public ItemDto updateItem(ItemDto itemDto) throws NotFoundException {
		Item item = itemRepository.findById(itemDto.getId()).orElseThrow(() -> new NotFoundException());
		item.setName(itemDto.getName());
		item.setPrice(itemDto.getPrice());
		itemRepository.flush();
		return new ItemDto(item);
	}

	public ItemDto deleteItem(Long id) throws NotFoundException {
		Item item = itemRepository.findById(id).orElseThrow(() -> new NotFoundException());
		ItemDto itemDto = new ItemDto(item);
		itemRepository.deleteById(id);
		return itemDto;
	}

}
