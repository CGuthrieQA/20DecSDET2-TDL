package com.qa.tdl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	public void contextTest() {
		Application.main( new String[] {} );
	}

}
