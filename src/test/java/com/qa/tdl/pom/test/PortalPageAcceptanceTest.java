package com.qa.tdl.pom.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.tdl.pom.pages.PortalPage;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PortalPageAcceptanceTest {
	
	// web driver init
	@Autowired
    private static WebDriver driver;
    
    // setup
    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
       
    }
    
    // TESTS IN HERE
    @Test
    public void createToDoListTest() {
    	// GIVEN - that I can navigate to the website
    	driver.get("http://127.0.0.1:9090/");
    	PortalPage website = PageFactory.initElements(driver, PortalPage.class);
    	System.out.println("Found the website");
    	
    	// WHEN - I create a to do list
    	website.createToDoList();
    	System.out.println("Created a To-Do list");
    	
    	// THEN - it exists in the database? and reads it from the database onto the page
    	website.waitToDoListRead(driver);
    	String result = website.getToDoListName();
    	String expected = "Foo";
    	assertNotNull(result);
    	System.out.println("Created To-Do list exists");
    	assertEquals(result, expected);
    	System.out.println("Created To-Do list has the expected value");
    }
    
    @Test
    public void readToDoListTest() {
    	// GIVEN - that I can navigate to the website
    	driver.get("http://127.0.0.1:9090/");
    	PortalPage website = PageFactory.initElements(driver, PortalPage.class);
    	System.out.println("Found the website");
    	
    	// WHEN - the website loads it gets a list of to-do lists from the database 
    	
    	// THEN - I can see the to-do lists on the page
    	String result = website.getToDoListName();
    	String expected = "Foo";
    	assertNotNull(result);
    	System.out.println("Read To-Do list exists");
    	assertEquals(result, expected);
    	System.out.println("Read To-Do list has the expected value");
    }
    
    // tear down
    @AfterAll
    public static void tearDown() {
        driver.quit();
        System.out.println("driver closed");
    }
}
