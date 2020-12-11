package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.TasksPage;
import com.crm.qa.util.TestUtil;

public class TasksPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	TasksPage tasksPage;
	
	String sheetName = "tasks";

	@BeforeMethod
	public void setUp() {
		initialization();
		testUtil = new TestUtil();
		tasksPage = new TasksPage();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToMainFrame();
		tasksPage = homePage.clickOnTaskLink();

	}

	@Test(priority = 1)
	public void verifyTaskLabelTest() {
		Assert.assertTrue(tasksPage.verifyTaskLabel(), "Task name missing");

	}

	@DataProvider
	public Object[][] getCRMtestData() {
		Object data[][]= TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority = 2, dataProvider = "getCRMtestData")
	public void VerifycreateNewTaskByBtn(String title, String taskType, String priority) {
	//	tasksPage.createNewTaskByBtn("Mr.", "Training", "Low");
		tasksPage.createNewTaskByBtn(title, taskType, priority);	
	}
	
	@Test(priority = 3)
	public void verifyClickOnTask() throws InterruptedException {
		tasksPage.clickOnTask("John");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}

}
