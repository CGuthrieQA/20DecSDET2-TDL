package com.qa.tdl.persistance.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ToDoListUnitTest {
	
	private Long id = 1L;
	private String name = "test name";
	private boolean complete = false;
	
	private ToDoList smallToDoList = new ToDoList(id);
	private ToDoList mediumToDoList = new ToDoList(name, complete);
	private ToDoList bigToDoList = new ToDoList(id, name, complete);
	
	@Test
	void constuctorOneTest() throws Exception {
		ToDoList result = new ToDoList(id);
		assertTrue(result instanceof ToDoList); // if it is not a valid Item then fail
		assertEquals( smallToDoList , result );
	}
	
	@Test
	void constuctorTwoTest() throws Exception {
		ToDoList result = new ToDoList(name, complete);
		assertTrue(result instanceof ToDoList); // if it is not a valid Item then fail
		assertEquals( mediumToDoList , result );
	}
	
	@Test
	void constuctorThreeTest() throws Exception {
		ToDoList result = new ToDoList(id, name, complete);
		assertTrue(result instanceof ToDoList); // if it is not a valid Item then fail
		assertEquals( bigToDoList , result );
	}
}
