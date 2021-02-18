package com.qa.tdl.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.qa.tdl.dto.ToDoListDto;
import com.qa.tdl.persistance.domain.ToDoList;
import com.qa.tdl.service.ToDoListService;

@SpringBootTest
@ActiveProfiles("dev")
public class ToDoListControllerTest {
	
	@Autowired
	private ToDoListController controller;
	
	@MockBean
	private ToDoListService service;
	
	@Autowired
	private ModelMapper mapper;
	
	private ToDoListDto mapToDTO(ToDoList toDoList) {
		return this.mapper.map(toDoList, ToDoListDto.class);
	}
	
	private ToDoList mapFromDTO(ToDoListDto toDoListDto) {
		return this.mapper.map(toDoListDto, ToDoList.class);
	}
	
	private final ToDoList testToDoList1 = new ToDoList(1L, "Foo", false);
	private final ToDoList testToDoList2 = new ToDoList(2L, "Bar", false);
	private final ToDoList testToDoList3 = new ToDoList(3L, "Shopping", false);
	private final ToDoList testToDoList4 = new ToDoList(4L, "Chores", false);
	
	private final List<ToDoList> listOfToDoLists = List.of(testToDoList1, testToDoList2, testToDoList3, testToDoList4);
	
	@Test
	void createTest() throws Exception {
		ToDoListDto newDto = this.mapToDTO(testToDoList1);
		
		when(this.service.create(newDto)).thenReturn(newDto);
		assertEquals( new ResponseEntity<ToDoListDto>(newDto, HttpStatus.CREATED) , (this.controller.create(newDto)) );
		
		verify(this.service, atLeastOnce()).create(newDto);
	}
	
	@Test
	void readAllTest() throws Exception {
		List<ToDoListDto> newDtoList = listOfToDoLists.stream().map(this::mapToDTO).collect(Collectors.toList());
		
		when(this.service.readAll()).thenReturn(newDtoList);
		assertEquals( ResponseEntity.ok(newDtoList) , this.controller.readAll() );
		
		verify(this.service, atLeastOnce()).readAll();
	}
	
}