package com.qa.tdl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.tdl.dto.ItemDto;
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
	public ResponseEntity<ItemDto> create(@RequestBody ItemDto itemDto) {
		ItemDto created = this.service.create(itemDto);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}
	
	// READ - get (all)
	@GetMapping("/read")
	public ResponseEntity<List<ItemDto>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}
	
	// get one
	@GetMapping("/read/{id}")
	public ResponseEntity<ItemDto> readById(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.readById(id));
	}
	
	// UPDATE - put
	@PutMapping("/update/{id}")
	public ResponseEntity<ItemDto> update(@PathVariable Long id, @RequestBody ItemDto itemDto) {
		return new ResponseEntity<>(this.service.update(itemDto, id), HttpStatus.ACCEPTED);
	}
	
	// DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ItemDto> delete(@PathVariable Long id) {
		return this.service.delete(id) ?
				new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
