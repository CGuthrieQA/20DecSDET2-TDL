package com.qa.tdl.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.tdl.dto.ItemDto;
import com.qa.tdl.persistance.domain.Item;
import com.qa.tdl.persistance.repo.ItemRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ItemService {
	
	private final ItemRepo repo;

	private final ModelMapper mapper;
	
	private ItemDto mapToDTO(Item item) {
		return this.mapper.map(item, ItemDto.class);
	}
	
	// CREATE
	public ItemDto create(Item item) {
		return this.mapToDTO(this.repo.save(item));
	}

}
