package com.qa.tdl.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.tdl.dto.ToDoListDto;
import com.qa.tdl.persistance.domain.ToDoList;
import com.qa.tdl.persistance.repo.ToDoListRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ToDoListService {

	private final ToDoListRepo repo;
	
	private final ModelMapper mapper;
	
	private ToDoListDto mapToDTO(ToDoList toDoList) {
		return this.mapper.map(toDoList, ToDoListDto.class);
	}
	
	// CREATE
	public ToDoListDto create(ToDoList toDoList) {
		return this.mapToDTO(this.repo.save(toDoList));
	}
	
}
