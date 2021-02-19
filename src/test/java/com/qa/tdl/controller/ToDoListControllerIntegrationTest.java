package com.qa.tdl.controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.tdl.dto.ToDoListDto;
import com.qa.tdl.persistance.domain.ToDoList;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
// add SQL to use for testing
@Sql(scripts = { "classpath:TDL-schema.sql","classpath:TDL-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
class ToDoListControllerIntegrationTest {
	
	// mock the MVC
	@Autowired
	private MockMvc mvc;
	
	// need something to generate JSON response (name this whatever)
	@Autowired
	private ObjectMapper jsonifier;
	
	@Autowired
	private ModelMapper mapper;
	
	// map to DTO
	private ToDoListDto mapToDTO(ToDoList toDoList) {
		return this.mapper.map(toDoList, ToDoListDto.class);
	}
	
	private final String URI = "/todolist";
	
	private final ToDoList testToDoList1 = new ToDoList(1L, "Foo", false);
	private final ToDoList testToDoList2 = new ToDoList(2L, "Bar", false);
	private final ToDoList testToDoList3 = new ToDoList(3L, "Lorem", false);
	private final ToDoList testToDoList4 = new ToDoList(4L, "Ipsum", false);
	
	private final List<ToDoList> listOfToDoLists = List.of(testToDoList1, testToDoList2, testToDoList3, testToDoList4);
	
	@Test
	void createTest() throws Exception {
		
	}
}
