package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactPage extends TestBase {

	@FindBy(xpath = "//td[contains(text(), 'Contacts')]")
	WebElement contactsLabel;
	
	@FindBy(xpath = "//select[@name = 'title']")
	WebElement title;
	
	@FindBy(xpath = "//input[@name = 'first_name']")
	WebElement firstname;
	
	@FindBy(xpath = "//input[@name = 'surname']")
	WebElement lastname;
	
	@FindBy(xpath = "//input[@name = 'client_lookup']")
	WebElement company;
	
	@FindBy(xpath = " //input[@value = 'Load From Company']//following::input[1]")
	WebElement saveBtn;
	
	public ContactPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean verifyContactsLabel() {
		return contactsLabel.isDisplayed();
	}
	
	public void clickOnContactName(String name) {
		WebElement contacts = driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td[@class='datalistrow']"
										+ "//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']"));
		contacts.click();
	}
	
	public void createNewContact(String salutation, String fstName, String lstName, String comp) {
		Select select = new Select(title);
		select.selectByVisibleText(salutation);
		
		firstname.sendKeys(fstName);
		lastname.sendKeys(lstName);
		company.sendKeys(comp);
		saveBtn.click();
	}
	
	
}
