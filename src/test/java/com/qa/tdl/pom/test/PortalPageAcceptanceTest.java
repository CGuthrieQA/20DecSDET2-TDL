package com.qa.tdl.pom.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.tdl.pom.pages.PortalPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class PortalPageAcceptanceTest {
	
	// extent init
	private static ExtentReports report;
	private static ExtentTest test;
	
	// web driver init
    private static RemoteWebDriver driver;
    
    // setup
    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();//chromeCfg());
        
        // extent
        report = new ExtentReports("target/extent_report.html", true);
        
    	driver.get("https://127.0.0.1:9090/");
    	PortalPage website = PageFactory.initElements(driver, PortalPage.class);
    }
    
    // TESTS IN HERE
    
    // extent ending after each
    @AfterEach
    public void afterTests(){
    	report.endTest(test);
    }
    
    // tear down
    @AfterAll
    public static void tearDown() {
        driver.quit();
        System.out.println("driver closed");
        
        report.endTest(test);
        report.flush();
        report.close();
        System.out.println("extent closed");
    }
}
