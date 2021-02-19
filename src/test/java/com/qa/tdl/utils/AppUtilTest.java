package com.qa.tdl.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
public class AppUtilTest {
	
	// this is using reflection, found on LaurentHinoul.com "how to test a private constructor"
	@Test
	void constructorTest() throws Exception {
		Constructor<AppUtil> privateConstructor = AppUtil.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(privateConstructor.getModifiers()));
		privateConstructor.setAccessible(true);
		privateConstructor.newInstance();
	}
	
}
