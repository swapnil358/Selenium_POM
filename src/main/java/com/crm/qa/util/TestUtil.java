package com.crm.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase {

	public static long PAGE_LOAD_TIMEOUTS = 30;
	public static long IMPLICIT_WAIT = 20;

	public static String TESTDATA_SHEET_PATH = "C:\\Users\\n\\eclipse-workspace\\WebDriverPracticeByNaveen\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\testdata\\freeCrmTestData.xlsx";

	static Workbook book;
	static org.apache.poi.ss.usermodel.Sheet sheet;
	static JavascriptExecutor js;

	/*
	 * Switch to frame
	 * 
	 */
	public void switchToLeftFrame() {
		driver.switchTo().frame("leftpanel");
	}

	public void switchToMainFrame() {
		driver.switchTo().frame("mainpanel");
	}

	/*
	 * Explicit wait
	 * 
	 */

	public static void clickOn(WebDriver driver, WebElement locator, int timeout) {
		new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class)
		.until(ExpectedConditions.elementToBeClickable(locator));
		
	}

	/*
	 * @author swapnil calender
	 */

	/*
	 * public void calender(String date) { String date = "29-September-2002"; String
	 * dateArr[] = date.split("-"); String day = dateArr[0]; String month =
	 * dateArr[1]; String years = dateArr[2];
	 * 
	 * Select selMonth = new Select(driver.findElement(By.name("slctMonth")));
	 * selMonth.selectByVisibleText(month);
	 * 
	 * Select selYear = new Select(driver.findElement(By.name("slctYear")));
	 * selYear.selectByVisibleText(years);
	 * 
	 * }
	 */

	/*
	 * 
	 * @test data reader
	 * 
	 */
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);     //WorkbookFactory ---Factory for creating the appropriate kind of 
		} catch (InvalidFormatException e) {		 //Workbook (be it HSSFWorkbook or XSSFWorkbook), 
			e.printStackTrace();					 //by auto-detecting from the supplied input.
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}

}
