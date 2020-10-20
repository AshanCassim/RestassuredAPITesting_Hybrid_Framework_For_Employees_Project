package com.employeeapi.utilities;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.employeeapi.base.TestBase;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.Assert;
import org.testng.annotations.AfterClass;


public class ExtReport extends TestBase {

	ExtentSparkReporter htmlreporter;
	public static ExtentReports extent;
	public static ExtentTest parentTest;
	public static ExtentTest childTest;
	
	
	@BeforeTest
	public void report() 
	{
		String DateName = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		htmlreporter = new ExtentSparkReporter("Reports/"+DateName+"@MyResult.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlreporter);
		extent.setSystemInfo("Project Name","Employee DatabaseAPI");
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environemnt","QA");
		extent.setSystemInfo("user","Ashan Cassim");
	}
	
	
	@BeforeMethod
	public void method(Method method) throws InterruptedException 
	{
	
		parentTest =  extent.createTest(method.getName());
		
	}
	
	
	@AfterClass
	public void afterClass() {
		  
		  extent.flush();
	}
	
	public void createNode(String fun)
	{
		childTest = parentTest.createNode(fun);
	
	}
	
	public void assertEqual(String Act,String Exp,String fun) {
	
	try {
		Assert.assertEquals(Act, Exp);
		childTest.pass("Successfull " + fun +" " + Exp);
		
	}
	 catch (AssertionError ex) {
		  
		    String msg = ex.getMessage();
		    childTest.fail("unSuccessfull " + fun + " " + msg);
		    // Re throw the AssertionError to signal to TestNG that the test failed.
		    
	}
	
	}
	
	public void assertTr(boolean Expression,String mssg) {
		
		try {
			Assert.assertTrue(Expression);
			childTest.pass(mssg);
			
		}
		 catch (AssertionError ex) {
			  
			    String msg = ex.getMessage();
			    childTest.fail("unSuccessfull" + msg);
			    // Re throw the AssertionError to signal to TestNG that the test failed.
			   
		}
		
		}

}

