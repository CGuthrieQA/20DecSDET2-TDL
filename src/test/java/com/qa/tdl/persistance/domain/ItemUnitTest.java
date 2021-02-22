package com.qa.tdl.persistance.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ItemUnitTest {
	
	private Long id = 1L;
	private String name = "test name";
	private boolean complete = false;

	private Item smallItem = new Item(name, complete);
	private Item bigItem = new Item(id, name, complete);
	
	@Test
	void constructorOneTest() throws Exception {
		Item result = new Item(name, complete);
		assertNotNull(result); // if empty break
		assertTrue(result instanceof Item); // if it is not a valid Item then fail
		assertEquals( smallItem , result );
	}
	
	@Test
	void constructorTwoTest() throws Exception {
		Item result = new Item(id, name, complete);
		assertNotNull(result); // if empty break
		assertTrue(result instanceof Item); // if it is not a valid Item then fail
		assertEquals( bigItem , result );
	}
	
}
