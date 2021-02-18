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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.tdl.dto.ToDoListDto;
import com.qa.tdl.service.ToDoListService;

@RestController
@CrossOrigin("http://127.0.0.1:9090")
@RequestMapping("/todolist")
public class ToDoListController {
	
	private ToDoListService service;
	
	@Autowired
	public ToDoListController(ToDoListService service) {
		this.service = service;
	}
	
	// CREATE - post
	@PostMapping("/create")
	public ResponseEntity<ToDoListDto> create(@RequestBody ToDoListDto toDoListDto) {
		ToDoListDto created = this.service.create(toDoListDto);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}
	
	// READ - get (all)
	@GetMapping("/read")
	public ResponseEntity<List<ToDoListDto>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}
	
	// get one
	@GetMapping("/read/{id}")
	public ResponseEntity<ToDoListDto> readById(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.readById(id));
	}
	
	// UPDATE - put
	@PutMapping("/update/{id}")
	public ResponseEntity<ToDoListDto> update(@PathVariable Long id, @RequestBody ToDoListDto toDoListDto) {
		return new ResponseEntity<>(this.service.update(toDoListDto, id), HttpStatus.ACCEPTED);
	}
	
	// DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ToDoListDto> delete(@PathVariable Long id) {
		return this.service.delete(id) ?
				new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
