package com.qa.tdl.persistance.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
public class ItemTest {
	
	private Long id = 1L;
	private String name = "test name";
	private boolean complete = false;

	private Item smallItem = new Item(name, complete);
	private Item bigItem = new Item(id, name, complete);
	
	@Test
	void constructorOneTest() throws Exception {
		assertEquals( smallItem , (new Item(name, complete)) );
	}
	
	@Test
	void constructorTwoTest() throws Exception {
		assertEquals( bigItem , (new Item(id, name, complete)) );
	}
	
}
