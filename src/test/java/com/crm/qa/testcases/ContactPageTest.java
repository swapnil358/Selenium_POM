package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactPageTest extends TestBase {
	LoginPage loginPage;
	ContactPage contactPage;
	HomePage homePage;
	TestUtil testUtil;

	String sheetName = "contacts";
	
	public ContactPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		contactPage = new ContactPage();
		testUtil = new TestUtil();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToMainFrame();
		contactPage = homePage.clickOnContactsLink();

	}

	@Test(priority = 1)
	public void verifyContactsLabelTest() {
		Assert.assertTrue(contactPage.verifyContactsLabel(), "Contact name missing");
		
	}

	@Test(priority = 2)
	public void selectSignleContactNameTest() {
		contactPage.clickOnContactName("John cena");
	}

	@Test(priority = 3)
	public void selectMultipleContactNameTest() {
		contactPage.clickOnContactName("Brendan lobban");
		contactPage.clickOnContactName("Saurabh sinha");
	}

	@DataProvider
	public Object[][] getCRMtestData() {
		Object data[][]= TestUtil.getTestData(sheetName);
		return data;
	}
	
	
     @Test(priority = 4, dataProvider = "getCRMtestData")
	public void validateCreateNewContactTest(String title, String firstname, String lastname, String company) {
		//homePage.MouseHoverOnContact();
		homePage.MouseHover(homePage.contactLink());
		//contactPage.createNewContact("Mr.", "Tom", "marsh", "TCS");
		contactPage.createNewContact(title, firstname, lastname, company);
	}
	
/*	@Test(priority =4)
	public void verifyCreateNewContact() {
		homePage.MouseHover(homePage.contactLink());
		contactPage.createNewContact("Mr.", "swapnil", "bodade", "cmputer");
	}
*/
	@AfterMethod
	public void tearDown() {
		driver.quit();

	}

}
