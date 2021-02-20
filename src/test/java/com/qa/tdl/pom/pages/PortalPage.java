package com.qa.tdl.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PortalPage {

	@FindBy(xpath = "//*[@id=\'input_todolist_name\']")
	private WebElement createToDoListInput;
	
	@FindBy(xpath = "//*[@id=\'create_todolist\']/div/div[2]/button")
	private WebElement createToDoListSubmit;

	@FindBy(xpath = "//*[@id=\"todolist-delete-button-1\"]")
	private WebElement deleteToDoListSubmit;

	@FindBy(xpath = "//*[@id=\"todolist-update-button-1\"]")
	private WebElement updateToDoListButton;
	
	@FindBy(xpath = "//*[@id=\"update-todolist-input-1\"]")
	private WebElement updateToDoListInput;
	
	@FindBy(xpath = "//*[@id=\"update-todolist-form-1\"]/div/div[2]/button")
	private WebElement updateToDoListSubmit;
	
	@FindBy(xpath = "//*[@id=\"update-todolist-form-1\"]/div/div[3]/button")
	private WebElement updateToDoListCancel;
	
	@FindBy(xpath = "//*[@id=\"todolist-name-1\"]")
	private WebElement toDoListName;
	
	@FindBy(xpath = "//*[@id=\"todolist-name-container-1\"]/div[2]/button")
	private WebElement toggleDropDownButton;
	
	@FindBy(xpath = "//*[@id=\"item-create-input-1\"]")
	private WebElement createItemInput;
	
	@FindBy(xpath = "//*[@id=\"1-item-create-button\"]")
	private WebElement createItemSubmit;
	
	@FindBy(xpath = "//*[@id=\"item-name-1\"]")
	private WebElement itemName;
	
	@FindBy(xpath = "//*[@id=\"item-delete-1\"]")
	private WebElement deleteItemSubmit;
	
	@FindBy(xpath = "//*[@id=\"item-update-1\"]")
	private WebElement updateItemButton;
	
	@FindBy(xpath = "//*[@id=\"update-item-input-1\"]")
	private WebElement updateItemInput;
	
	@FindBy(xpath = "//*[@id=\"update-item-form-1\"]/div/div[2]/button")
	private WebElement updateItemSubmit;
	
	@FindBy(xpath = "//*[@id=\"update-item-form-1\"]/div/div[3]/button")
	private WebElement updateItemCancel;
	
	// CONSTRUCTOR
	
	public PortalPage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// METHODS
	
	// To-Do List
	
	// CREATE
	public void createToDoListType() {
		createToDoListInput.sendKeys("Foo");
	}
	
	public void createToDoListSubmit() {
		createToDoListSubmit.click();
	}

	// READ
	public void waitToDoListRead(WebDriver driver) {
		WebElement waitToDoListReadElement = (new WebDriverWait(driver, 300))
						.until(ExpectedConditions
						.presenceOfElementLocated(By.id("todolist-name-1")));
	}
	
	public String getToDoListName() {
		return toDoListName.getText();
	}
	
	// UPDATE
	public void waitToDoListUpdate(WebDriver driver) {
		WebElement waitToDoListUpdateElement = (new WebDriverWait(driver, 300))
						.until(ExpectedConditions
						.presenceOfElementLocated(By.id("update-todolist-input-1")));
	}
	
	public void updateToDoListButton() {
		updateToDoListButton.click();
	}
	public void updateToDoListType() {
		updateToDoListInput.sendKeys("Bar");
	}
	public void updateToDoListSubmit() {
		updateToDoListSubmit.click();
	}
	public void updateToDoListCancel() {
		updateToDoListCancel.click();
	}
	
	// DELETE
	public void deleteToDoListSubmit() {
		deleteToDoListSubmit.click();
	}
	
	// TOGGLE BUTTON
	public void waitToggle(WebDriver driver) {
		WebElement waitToggleElement = (new WebDriverWait(driver, 300))
						.until(ExpectedConditions
						.presenceOfElementLocated(By.id("item-create-input-1")));
	}
	
	public void toggleItemsButton() {
		toggleDropDownButton.click();
	}
	
	// Item
	
	// CREATE
	public void createItemType() {
		createItemInput.sendKeys("Lorem");
	}
	public void createItemSubmit() {
		createItemSubmit.click();
	}
	
	// READ

	public void waitItemRead(WebDriver driver) {
		WebElement waitItemReadElement = (new WebDriverWait(driver, 300))
						.until(ExpectedConditions
						.presenceOfElementLocated(By.id("item-name-1")));
	}
	public String getItemName() {
		return itemName.getText().strip();
	}
	
	// UPDATE
	public void updateItem() {
		updateItemButton.click();
		updateItemInput.sendKeys("Ipsum");
		updateItemSubmit.click();
	}
	
	public void updateItemCancel() {
		updateItemButton.click();
		updateItemCancel.click();
	}
	
	// DELETE// DELETE
	public void deleteItem() {
		deleteItemSubmit.click();
	}
}
