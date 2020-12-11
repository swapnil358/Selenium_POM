package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {

	// PageFactory

	@FindBy(xpath = "//td[contains(text(),'User: swapnil bodade')]")
	WebElement userNameLabel;

	@FindBy(xpath = "//a[contains(text(),'Contacts')]")
	WebElement contactsLink;

	@FindBy(xpath = "//a[contains(text(),'New Contact')]")
	WebElement newContactLink;

	@FindBy(xpath = "//a[contains(text(),'Deals')]")
	WebElement dealsLink;

	@FindBy(xpath = "//a[contains(text(),'Tasks')]")
	WebElement TasksLink;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public WebElement contactLink() {
		return contactsLink;
	}

	public String verifyHomePageTitle() {
		return driver.getTitle();
	}

	public ContactPage clickOnContactsLink() {
		contactsLink.click();
		return new ContactPage();
	}

	public DealsPage clickOnDealsLink() {
		dealsLink.click();
		return new DealsPage();
	}

	public TasksPage clickOnTaskLink() {
		TasksLink.click();
		return new TasksPage();
	}

	public boolean verifyUserNameLabel() {
		return userNameLabel.isDisplayed();
	}
	

	public void MouseHover(WebElement webelement) {
		Actions action = new Actions(driver);
		action.moveToElement(webelement).build().perform();

	}

	/*
	 * public void MouseHoverOnContact() { Actions action = new Actions(driver);
	 * action.moveToElement(contactsLink).build().perform(); newContactLink.click();
	 * }
	 */

}