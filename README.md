Testing Coverage: 97.0%

# To-Do List Project

OOP-based web application, with utilisation of supporting tools, methodologies, and technologies, that encapsulates all fundamental and practical modules covered during training at QA.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Software required

```
MySQL (version 8.0 or higher)
Java (version 11.0 or higher)
Apache Maven
Git
```

### Installing

Make sure you have the prerequisites installed, then you can download the latest release from the releases tab.

Once you have the .jar file downloaded make sure to navigate with your command line interface to the folder it is stored in.

You can run it through the command line with

```
java -jar 1.0-fat.jar
```

## Running the tests

To run tests on this project you must clone the repository down to your local system

```git
git clone https://github.com/CGuthrieQA/20DecSDET2-TDL.git
```

You can then navigate to your local version of the repo with a command line interface and run tests using maven.

```
mvn test
```

### Unit Tests 

Unit tests were created to test almost every line written, the CRUD functionality was the most important and the only lines skipped were a private constructor, as creating a reflected instance to test was out of scope, and the main method.

### Unit Test Example

```java
@Test
void constructorOneTest() throws Exception {
	Item result = new Item(name, complete);
	Item expected = testItem;
	assertNotNull(result);
	assertTrue(result instanceof Item);
	assertEquals( expected , result );
}
```

### Integration Tests

Integration tests were created to test the CRUD functionality in the controllers and how they communicate with the services.

### Integration Test Example

```java
@Test
void readAllTest() throws Exception {
	
	List<ItemDto> testDtoList = 
			listItems.stream()
			.map(this::mapToDTO)
			.collect(Collectors.toList());
	
	MockHttpServletRequestBuilder mockRequest = 
			MockMvcRequestBuilders
			.request(HttpMethod.GET, URI + "/read")
			.contentType(MediaType.APPLICATION_JSON);
	
	ResultMatcher status = 
			MockMvcResultMatchers
			.status()
			.isOk();
	
	ResultMatcher contents = 
			MockMvcResultMatchers
			.content()
			.json(this.jsonifier.writeValueAsString(testDtoList));
	
	mock.perform(mockRequest)
	.andExpect(status)
	.andExpect(contents);
}
```

### Acceptance Tests

Acceptance tests were used to test the User Stories on the front end of the application. Mainly CRUD functionallity but also navigation.

### Acceptance Test Example

```java
@Test
@Order(1)
void createToDoListTest() {
	// GIVEN - that I can navigate to the website
	driver.get("http://127.0.0.1:9090/");
	PortalPage website = PageFactory.initElements(driver, PortalPage.class);
	
	// WHEN - I enter a new to do list name
	website.createToDoListType();
	
	// AND - I click the button to create a new to do list
	website.createToDoListSubmit();
	
	// THEN - it reads it from the database onto the page
	website.waitToDoListRead(driver);
	String result = website.getToDoListName();
	String expected = "Foo";
	
	assertNotNull(result);
	assertEquals(expected, result);
}
```

## Deployment

As this project is running on an H2 database using Spring Boot, there is no need to manually create a local SQL database.

If you would like to change the project to be deployed in this way though, you can run these commands to set up the correct database and tables

```SQL
DROP SCHEMA tdl;

CREATE SCHEMA IF NOT EXISTS `tdl`;

USE `tdl` ;

DROP table IF EXISTS `TO_DO_LIST` CASCADE;
DROP table IF EXISTS `ITEM` CASCADE;

CREATE table IF NOT EXISTS `TO_DO_LIST` (
	`id` INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	`complete` BIT NOT NULL
);

CREATE table IF NOT EXISTS `ITEM` (
	`id` INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	`complete` BIT NOT NULL,
	`TO_DO_LIST_ID` INT(11)
);
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Cameron Guthrie** - *Project* - [CGuthrieQA](https://github.com/CGuthrieQA/)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*