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
import com.qa.tdl.dto.ItemDto;
import com.qa.tdl.dto.ToDoListDto;
import com.qa.tdl.persistance.domain.Item;
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
	// map item to DTO
	private ItemDto mapItemToDTO(Item item) {
		return this.mapper.map(item, ItemDto.class);
	}
	
	private final String URI = "/todolist";
	
	private final ToDoList testToDoList1 = new ToDoList("test5", false); // id = 5L
	
	// to do lists from data.sql
	private final ToDoList dataToDoList1 = new ToDoList(1L, "Foo", false);
	private final ToDoList dataToDoList2 = new ToDoList(2L, "Bar", false);
	private final ToDoList dataToDoList3 = new ToDoList(3L, "Lorem", false);
	private final ToDoList dataToDoList4 = new ToDoList(4L, "Ipsum", false);
	
	// items from data.sql
	private final Item dataItem1 = new Item(1L, "Foo", false);
	private final Item dataItem2 = new Item(2L, "Bar", false);
	private final Item dataItem3 = new Item(3L, "Lorem", false);
	private final Item dataItem4 = new Item(4L, "Ipsum", false);
	
	
//	private final List<ToDoList> listOfToDoLists = List.of(dataToDoList1, dataToDoList2, dataToDoList3, dataToDoList4);
	
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
	
	@Test
	void readAllTest() throws Exception {
//		List<ToDoListDto> testDtoList = 
//				listOfToDoLists.stream()
//				.map(this::mapToDTO)
//				.collect(Collectors.toList());
		
		ToDoListDto testDto1 = this.mapToDTO(dataToDoList1);
		testDto1.setItems(List.of(this.mapItemToDTO(dataItem1)));
		
		ToDoListDto testDto2 = this.mapToDTO(dataToDoList2);
		testDto2.setItems(List.of(this.mapItemToDTO(dataItem2)));
		
		ToDoListDto testDto3 = this.mapToDTO(dataToDoList3);
		testDto3.setItems(List.of(this.mapItemToDTO(dataItem3)));
		
		ToDoListDto testDto4 = this.mapToDTO(dataToDoList4);
		testDto4.setItems(List.of(this.mapItemToDTO(dataItem4)));
		
		List<ToDoListDto> testDtoList = List.of(testDto1, testDto2, testDto3, testDto4);
		
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders
				.request(HttpMethod.GET, URI + "/read")
				.contentType(MediaType.APPLICATION_JSON);
				//.content(this.jsonifier.writeValueAsString(testToDoList1))
				//.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher status = 
				MockMvcResultMatchers
				.status()
				.isOk(); // this needs to match HTTP status
		
		ResultMatcher contents = 
				MockMvcResultMatchers
				.content()
				.json(this.jsonifier.writeValueAsString(testDtoList));
		
		mock.perform(mockRequest)
		.andExpect(status)
		.andExpect(contents);
	}
	
	@Test
	void readByIdTest() throws Exception {
		
		Long id = 1L;
		
		ToDoListDto testDto1 = this.mapToDTO(dataToDoList1);
		testDto1.setItems(List.of(this.mapItemToDTO(dataItem1)));
		
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders
				.request(HttpMethod.GET, URI + "/read" + "/" + id)
				.contentType(MediaType.APPLICATION_JSON);
		
		ResultMatcher status = 
				MockMvcResultMatchers
				.status()
				.isOk();
		
		ResultMatcher contents = 
				MockMvcResultMatchers
				.content()
				.json(this.jsonifier.writeValueAsString(testDto1));
		
		mock.perform(mockRequest)
		.andExpect(status)
		.andExpect(contents);
	}
	
	@Test
	void updateTest() throws Exception {
		
		Long id = 3L;
		
		ToDoListDto testDto3 = this.mapToDTO(dataToDoList3);
		testDto3.setItems(List.of(this.mapItemToDTO(dataItem3)));
		
		ToDoListDto newTestDto3 = testDto3;
		newTestDto3.setName("I am a test");
		
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders
				.request(HttpMethod.PUT, URI + "/update" + "/" + id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(newTestDto3))
				.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher status = 
				MockMvcResultMatchers
				.status()
				.isAccepted();
		
		ResultMatcher contents = 
				MockMvcResultMatchers
				.content()
				.json(this.jsonifier.writeValueAsString(newTestDto3));
		
		mock.perform(mockRequest)
		.andExpect(status)
		.andExpect(contents);
	}
	
}
