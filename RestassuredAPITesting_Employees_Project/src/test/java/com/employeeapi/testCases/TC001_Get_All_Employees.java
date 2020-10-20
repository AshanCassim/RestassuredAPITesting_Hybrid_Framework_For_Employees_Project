/******************************************************
Test Name:Get all employees data
URI: http://dummy.restapiexample.com/api/v1/employees
Request Type: GET
Request Pay load(Body): NA
********* Validations **********
Status Code : 200
Status Line : HTTP/1.1 200 OK
Content Type : text/html; charset=UTF-8
Server Type :  nginx/1.14.1
Content Encoding : gzip
Content Length <800
 *********************************************************/

package com.employeeapi.testCases;

import org.testng.annotations.*;
import com.employeeapi.utilities.ExtReport;
import io.restassured.RestAssured;
import io.restassured.http.Method;



public class TC001_Get_All_Employees extends ExtReport{

		
	@BeforeClass
	void getAllEmployees() throws InterruptedException
	{
	
	logger.info("*********Started TC001_Get_All_Employees **********");
	
	
	RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
	httpRequest = RestAssured.given();
	response = httpRequest.request(Method.GET,"/employees");
	
	
	
	Thread.sleep(5);
	}
	
			
	@Test
	public void  TC001_Get_All_Employees_D() 
	{
		ExtReport sb = new ExtReport();
		
		logger.info("***********  Checking Respose Body **********");
		sb.createNode("Checking Respose Body");
		
		
		String responseBody = response.getBody().asString();
		logger.info("Response Body==>"+responseBody);
		
		if(responseBody != null)
		{
			
			childTest.pass("Successfull responseBody ==>" + responseBody);
			
		}
		else 
		{
			childTest.fail("unSuccessfull responseBody ==>" + responseBody);
		}
		
	
		logger.info("***********  Checking Status Code **********");
		sb.createNode("Checking Status Code");
		
		
		int statusCode = response.getStatusCode(); // Getting status code
		logger.info("Status Code is ==>" + statusCode); //200
		
		if(statusCode == 200)
		{
			
			childTest.pass("Successfull Status Code is ==>" + statusCode);
			
		} 
		else
		{
			childTest.fail("unSuccessfull Status Code is ==>" + statusCode);
		}
		
		
	
		logger.info("***********  Checking Response Time **********");
		sb.createNode("Checking Response Time");
		
		
		long responseTime = response.getTime(); // Getting status Line
		logger.info("Response Time is ==>" + responseTime);
		
		if(responseTime>5000) 
		{
			logger.warn("Response Time is greater than 2000");
		    childTest.warning("Response Time is greater than 2000 ,actual is " + responseTime);
		
		}
		else
		{
			childTest.pass("responseTime<10000 ,actual is " + responseTime);
		}
		
		
		
		logger.info("***********  Checking Status Line **********");
		sb.createNode("Checking Status Line");
		
		
		String statusLine = response.getStatusLine(); // Getting status Line
		logger.info("Status Line is ==>" + statusLine);
		
		sb.assertEqual(statusLine, "HTTP/1.1 200 OK","statusLine");
		
		
	
		logger.info("***********  Checking Content Type **********");
		sb.createNode("Checking Content Type");
		
		
		String contentType = response.header("Content-Type");
		logger.info("Content type is ==>" + contentType);
		sb.assertEqual(contentType,"application/json","contentType");
		
	
		logger.info("***********  Checking Server Type **********");
		sb.createNode("Checking Server Type");
		
		
		String serverType = response.header("Server");
		logger.info("Server Type is =>" + serverType);
		sb.assertEqual(serverType,"nginx/1.16.0","serverType");
		

		logger.info("***********  Checking Content Lenght**********");
		sb.createNode("Checking Content Lenght");
		
		
		String contentLength = response.header("Content-Length");
		logger.info("Content Length is==>" +contentLength); 
		
		if(Integer.parseInt(contentLength)<100)
			logger.warn("Content Length is less than 100");
		
		sb.assertTr(Integer.parseInt(contentLength)>100, "Content Length is greater than 100");
		
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC001_Get_All_Employees **********");
	}
				 	
}