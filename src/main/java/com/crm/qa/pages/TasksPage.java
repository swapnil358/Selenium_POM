package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class TasksPage extends TestBase {

	@FindBy(xpath = "//td[contains(text(),'Tasks')]")
	WebElement tasksLabel;

	@FindBy(xpath = "//input[@value = 'New Task']")
	WebElement newTaskBtn;

	@FindBy(xpath = "//a[contains(text(),'New Task')]")
	WebElement newTaskLink;

	@FindBy(xpath = "//input[@name='title']")
	WebElement newTaskTitle;

	@FindBy(xpath = "//select[@name='status']")
	WebElement newTaskStatus;

	@FindBy(xpath = "//select[@name='task_type']")
	WebElement newTaskType;

	@FindBy(xpath = "//select[@name='priority']")
	WebElement newTaskPriority;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement newTaskSubmit;

	@FindBy(xpath = "//a[contains(@href, 'https://classic.crmpro.com/system/index.cfm?action=task&sub=search')]")
	WebElement fullSearchTaskLink;

	public TasksPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean verifyTaskLabel() {
		return tasksLabel.isDisplayed();

	}

	public void createNewTaskByBtn(String title, String taskType, String priority) {

		newTaskBtn.click();
		newTaskTitle.sendKeys(title);
		Select selTask = new Select(newTaskType);
		selTask.selectByVisibleText(taskType);

		Select selPriority = new Select(newTaskPriority);
		selPriority.selectByVisibleText(priority);
		newTaskSubmit.click();
	}

	/*
	 * @author swapnil click on task
	 */

	public void clickOnTask(String enterTaskName) throws InterruptedException {

		String Before_xpath = "//body/table/tbody/tr/td/table/tbody/tr/td/form[@name='TASKSEARCH']/table[@class='datacard']/tbody/tr[";
		String After_xpath = "]/td[2]";

		Thread.sleep(1000);

		for (int i = 3; i <= 13; i++) {
			String name = driver.findElement(By.xpath(Before_xpath + i + After_xpath)).getText();
			System.out.println(name);
			Thread.sleep(500);
			if (name.contains(enterTaskName)) {
				WebElement tsk = driver.findElement(By.xpath(
						"//body/table/tbody/tr/td/table/tbody/tr/td/form[@name='TASKSEARCH']/table[@class='datacard']/tbody/tr["
								+ i + "]/td[2]/a"));
				tsk.click();
				break;
			}

		}
	}

	public void flagForUser(WebElement element) {
			
	}
	
}
