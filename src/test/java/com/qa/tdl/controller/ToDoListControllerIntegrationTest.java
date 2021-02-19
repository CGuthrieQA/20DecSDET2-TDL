package com.qa.tdl.controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.tdl.dto.ToDoListDto;
import com.qa.tdl.persistance.domain.ToDoList;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@Sql(scripts = { "classpath:TDL-schema.sql","classpath:TDL-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
class ToDoListControllerIntegrationTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ObjectMapper jsonifier;
	
	@Autowired
	private ModelMapper mapper;
	
	// map to DTO
	private ToDoListDto mapToDTO(ToDoList toDoList) {
		return this.mapper.map(toDoList, ToDoListDto.class);
	}
	
	private final String URI = "/todolist";
	
	private final ToDoList testToDoList1 = new ToDoList("test5", false); // id = 5L
	
	// to do lists from data.sql
	private final ToDoList dataToDoList1 = new ToDoList(1L, "Foo", false);
	private final ToDoList dataToDoList2 = new ToDoList(2L, "Bar", false);
	private final ToDoList dataToDoList3 = new ToDoList(3L, "Lorem", false);
	private final ToDoList dataToDoList4 = new ToDoList(4L, "Ipsum", false);
	
	private final List<ToDoList> listOfToDoLists = List.of(dataToDoList1, dataToDoList2, dataToDoList3, dataToDoList4, testToDoList1);
	
	@Test
	void createTest() throws Exception {
		// STEPS //
		
		ToDoListDto testDto = this.mapToDTO(testToDoList1); // running from the mapToDto (before IDs are loaded)
		testDto.setId(5L);  // expected id
		
		// RESOURCES
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders
				.request(HttpMethod.POST, URI + "/create") // remember to change the action as well as the path
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(testToDoList1))
				.accept(MediaType.APPLICATION_JSON);
		
		// ASSERTION (what happens)
		ResultMatcher status = 
				MockMvcResultMatchers
				.status()
				.isCreated(); // this needs to match HTTP status
		
		ResultMatcher contents = 
				MockMvcResultMatchers
				.content()
				.json(this.jsonifier.writeValueAsString(testDto));

		// ACTION (build request)
		mock.perform(mockRequest)
		.andExpect(status)
		.andExpect(contents);
	}
	
}
