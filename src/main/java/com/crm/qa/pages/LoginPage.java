package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {

	// PageFactory : OR

	@FindBy(name = "email")
	WebElement username;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(xpath = "//div[starts-with(text(),'Login')]")
	WebElement loginBtn;

	@FindBy(xpath = "//a[contains(text, 'Sign Up')]")
	WebElement signUpBtn;

	@FindBy(xpath = "//a[@href='https://classic.crmpro.com/index.html']")							//img[contains(@class, 'img-responsive')]
	WebElement crmLogo;

	
	//Initializing the Page Objects//Page Factory
	public LoginPage() {
		PageFactory.initElements(driver, this);
		// or PageFactory.initElements(driver, LoginPage.class);
	} 
	
	//Actions Methods for Login Page
	
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public boolean validateCRMImage() {
		return crmLogo.isDisplayed();
	
	}
	
	public HomePage login(String un, String pwd) {
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginBtn.click();
		
		return new HomePage();
		
	}
}
