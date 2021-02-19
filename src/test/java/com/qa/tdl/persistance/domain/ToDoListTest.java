package com.qa.tdl.persistance.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class ToDoListTest {
	
	private Long id = 1L;
	private String name = "test name";
	private boolean complete = false;
	
	private ToDoList smallToDoList = new ToDoList(id);
	private ToDoList mediumToDoList = new ToDoList(name, complete);
	private ToDoList bigToDoList = new ToDoList(id, name, complete);
	
	@Test
	void constuctorOneTest() throws Exception {
		assertEquals( smallToDoList , (new ToDoList(id)) );
	}
	
	@Test
	void constuctorTwoTest() throws Exception {
		assertEquals( mediumToDoList , (new ToDoList(name, complete)) );
	}
	
	@Test
	void constuctorThreeTest() throws Exception {
		assertEquals( bigToDoList , (new ToDoList(id, name, complete)) );
	}
}
