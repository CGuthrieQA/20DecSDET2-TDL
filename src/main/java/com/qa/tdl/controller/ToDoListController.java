package com.qa.tdl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.tdl.dto.ToDoListDto;
import com.qa.tdl.persistance.domain.ToDoList;
import com.qa.tdl.service.ToDoListService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/todolist")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ToDoListController {

	private ToDoListService service;
	
	// CREATE - post
	@PostMapping("/create")
	public ResponseEntity<ToDoListDto> create(@RequestBody ToDoList toDoList) {
		ToDoListDto created = this.service.create(toDoList);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}
	
	// READ - get (all)
	@GetMapping("/read")
	public ResponseEntity<List<ToDoListDto>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}
	
}
