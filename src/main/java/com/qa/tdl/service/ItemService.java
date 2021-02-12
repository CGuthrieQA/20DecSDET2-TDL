package com.qa.tdl.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.tdl.dto.ItemDto;
import com.qa.tdl.persistance.domain.Item;
import com.qa.tdl.persistance.repo.ItemRepo;
import com.qa.tdl.utils.AppUtil;

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
	
	// READ ALL
	public List<ItemDto> readAll() {
		return this.repo.findAll()
				.stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	// READ ONE
	public ItemDto readById(Long id) {
		return this.mapToDTO(this.repo.findById(id)
				.orElseThrow()); // custom exception later maybe?
	}
	
	// UPDATE
	public ItemDto update(ItemDto itemDto, Long id) {
		Item toUpdate = this.repo.findById(id)
				.orElseThrow(); // custom exception later maybe?
		toUpdate.setName(itemDto.getName());
		AppUtil.mergeNotNull(itemDto, toUpdate);
		return this.mapToDTO(this.repo.save(toUpdate));
	}

	// DELETE
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
	
}
