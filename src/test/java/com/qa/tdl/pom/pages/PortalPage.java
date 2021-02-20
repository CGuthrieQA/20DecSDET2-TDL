package com.qa.tdl.pom.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PortalPage {

	@FindBy(xpath = "//*[@id=\'input_todolist_name\']")
	private WebElement createToDoListInput;
	
	@FindBy(xpath = "//*[@id=\'create_todolist\']/div/div[2]/button")
	private WebElement createToDoListSubmit;
	
	@FindBy(css = "#posts > " // posts
			+ "div:nth-child(1) > " // article
			+ "div:nth-child(1) > " // inner
			+ "div:nth-child(1) > " // 1st column
			+ "div:nth-child(1) > " // flex row
			+ "div:nth-child(1) > " // 1st flex element
			+ "button") // button
	private WebElement deleteToDoListSubmit;
	
	@FindBy(css = "#posts > " // posts
			+ "div:nth-child(1) > " // article
			+ "div:nth-child(1) > " // inner
			+ "div:nth-child(1) > " // 1st column
			+ "div:nth-child(1) > " // flex row
			+ "div:nth-child(1) > " // 2nd flex element
			+ "button") // button
	private WebElement updateToDoListButton;
	
	@FindBy(css = "#posts > " // posts
		+ "div:nth-child(1) > " // article
		+ "div:nth-child(1) > " // inner
		+ "div:nth-child(3) > " // 2nd column (pill is at this level as well)
		+ "div:nth-child(1) > " // flex name container
		+ "div:nth-child(1) > " // form
		+ "div:nth-child(1) > " // flex display container
		+ "div:nth-child(1) > " // 1st flex item
		+ "input") // input
	private WebElement updateToDoListInput;
	
	@FindBy(css = "#posts > " // posts
		+ "div:nth-child(1) > " // article
		+ "div:nth-child(1) > " // inner
		+ "div:nth-child(3) > " // 2nd column (pill is at this level as well)
		+ "div:nth-child(1) > " // flex name container
		+ "div:nth-child(1) > " // form
		+ "div:nth-child(1) > " // flex display container
		+ "div:nth-child(2) > " // 2nd flex item
		+ "button") // button
	private WebElement updateToDoListSubmit;
	
	@FindBy(css = "#posts > " // posts
		+ "div:nth-child(1) > " // article
		+ "div:nth-child(1) > " // inner
		+ "div:nth-child(3) > " // 2nd column (pill is at this level as well)
		+ "div:nth-child(1) > " // flex name container
		+ "div:nth-child(1) > " // form
		+ "div:nth-child(1) > " // flex display container
		+ "div:nth-child(3) > " // 3rd flex item
		+ "button") // button
	private WebElement updateToDoListCancel;
	
	@FindBy(css = "#posts > " // posts
			+ "div:nth-child(1) > " // article
			+ "div:nth-child(1) > " // inner
			+ "div:nth-child(3) > " // 2nd column (pill is at this level as well)
			+ "div:nth-child(1) > " // flex name container
			+ "div:nth-child(1) > " // 1st flex element
			+ "h6") // header
	private WebElement toDoListName;
	
	@FindBy(css = "#posts > " // posts
			+ "div:nth-child(1) > " // article
			+ "div:nth-child(1) > " // inner
			+ "div:nth-child(3) > " // 2nd column (pill is at this level as well)
			+ "div:nth-child(1) > " // flex name container
			+ "div:nth-child(2) > " // 2nd flex element
			+ "button") // button
	private WebElement toggleDropDownButton;
	
	@FindBy(css = "#posts > " // posts
		+ "div:nth-child(1) > " // article
		+ "div:nth-child(1) > " // inner
		+ "div:nth-child(4) > " // toggle element
		+ "div:nth-child(1) > " // form
		+ "div:nth-child(1) > " // flex display div
		+ "div:nth-child(1) > " // 1st flex element
		+ "input") // input
	private WebElement createItemInput;
	
	@FindBy(css = "#posts > " // posts
		+ "div:nth-child(1) > " // article
		+ "div:nth-child(1) > " // inner
		+ "div:nth-child(4) > " // toggle element
		+ "div:nth-child(1) > " // form
		+ "div:nth-child(1) > " // flex display div
		+ "div:nth-child(2) > " // 2nd flex element
		+ "button") // button
	private WebElement createItemSubmit;
	
	@FindBy(css = "#posts > " // posts
		+ "div:nth-child(1) > " // article
		+ "div:nth-child(1) > " // inner
		+ "div:nth-child(4) > " // toggle element
		+ "div:nth-child(2) > " // the item
		+ "div:nth-child(2) > " // flex display div
		+ "div:nth-child(1) > " // 1st flex element
		+ "p") // paragraph
	private WebElement itemName; //this will need to be sliced
	
	@FindBy(css = "#posts > " // posts
		+ "div:nth-child(1) > " // article
		+ "div:nth-child(1) > " // inner
		+ "div:nth-child(4) > " // toggle element
		+ "div:nth-child(2) > " // the item
		+ "div:nth-child(2) > " // flex display div
		+ "div:nth-child(3) > " // 3rd flex element
		+ "button") // button
	private WebElement deleteItemSubmit;
	
	@FindBy(css = "#posts > " // posts
		+ "div:nth-child(1) > " // article
		+ "div:nth-child(1) > " // inner
		+ "div:nth-child(4) > " // toggle element
		+ "div:nth-child(2) > " // the item
		+ "div:nth-child(2) > " // flex display div
		+ "div:nth-child(2) > " // 2nd flex element
		+ "button") // button
	private WebElement updateItemButton;
	
	@FindBy(css = "#posts > " // posts
		+ "div:nth-child(1) > " // article
		+ "div:nth-child(1) > " // inner
		+ "div:nth-child(4) > " // toggle element
		+ "div:nth-child(2) > " // the item
		+ "div:nth-child(2) > " // flex display div
		+ "div:nth-child(1) > " // 1st flex element
		+ "div:nth-child(1) > " // form
		+ "div:nth-child(1) > " // flex display div
		+ "div:nth-child(1) > " // 1st flex element
		+ "input") // input
	private WebElement updateItemInput;
	
	@FindBy(css = "#posts > " // posts
		+ "div:nth-child(1) > " // article
		+ "div:nth-child(1) > " // inner
		+ "div:nth-child(4) > " // toggle element
		+ "div:nth-child(2) > " // the item
		+ "div:nth-child(2) > " // flex display div
		+ "div:nth-child(1) > " // 1st flex element
		+ "div:nth-child(1) > " // form
		+ "div:nth-child(1) > " // flex display div
		+ "div:nth-child(2) > " // 2nd flex element
		+ "button") // button
	private WebElement updateItemSubmit;
	
	@FindBy(css = "#posts > " // posts
		+ "div:nth-child(1) > " // article
		+ "div:nth-child(1) > " // inner
		+ "div:nth-child(4) > " // toggle element
		+ "div:nth-child(2) > " // the item
		+ "div:nth-child(2) > " // flex display div
		+ "div:nth-child(1) > " // 1st flex element
		+ "div:nth-child(1) > " // form
		+ "div:nth-child(1) > " // flex display div
		+ "div:nth-child(3) > " // 3rd flex element
		+ "button") // button
	private WebElement updateItemCancel;
	
}
