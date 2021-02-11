package com.qa.tdl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.tdl.dto.ItemDto;
import com.qa.tdl.persistance.domain.Item;
import com.qa.tdl.service.ItemService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/item")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ItemController {

	private ItemService service;
	
	// CREATE - post
	@PostMapping("/create")
	public ResponseEntity<ItemDto> create(@RequestBody Item item) {
		ItemDto created = this.service.create(item);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}
}
