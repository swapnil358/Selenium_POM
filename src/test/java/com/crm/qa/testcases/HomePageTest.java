package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.TasksPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactPage contactPage;
	TasksPage tasksPage;

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		testUtil = new TestUtil();
		contactPage = new ContactPage();
		tasksPage = new TasksPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void verifyHomePageTitleTest() {
		String HomePageTitle = homePage.verifyHomePageTitle();
		System.out.println("HomePageTitle: "+ HomePageTitle);
		Assert.assertEquals(HomePageTitle, "CRMPRO", "Home page title not matched");
	}

	@Test(priority = 2)
	public void userNameLabelTest() {
		testUtil.switchToMainFrame();
		Assert.assertTrue(homePage.verifyUserNameLabel());
	}
	
	@Test(priority = 4)
	public void verifyContactLinkClickTest() {
		testUtil.switchToMainFrame();
		contactPage = homePage.clickOnContactsLink();
	}
	
	@Test(priority = 3)
	public void verifyclickOnTaskLinkTest() {
		testUtil.switchToMainFrame();
		tasksPage = homePage.clickOnTaskLink();
	}
	
	
	

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}

}
