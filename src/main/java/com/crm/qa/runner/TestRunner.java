package com.crm.qa.runner;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class TestRunner {

	public static void main(String[] args) throws FileNotFoundException {

		
		//RUnning the failed test cases
		//mukesh otwani video
		
		TestNG runner = new TestNG();			//create object of TestNG class
		
		List<String> list = new ArrayList<String>();
		
		list.add("C:\\Users\\n\\eclipse-workspace\\WebDriverPracticeByNaveen\\FreeCRMTest\\test-output\\testng-failed.xml");
		
		runner.setTestSuites(list);
		
		runner.run();
		
		
		
	}

}
