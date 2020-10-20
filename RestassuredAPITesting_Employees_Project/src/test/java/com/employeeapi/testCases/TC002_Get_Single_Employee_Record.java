/******************************************************
Test Name:Get a single employee data
URI: http://dummy.restapiexample.com/api/v1/employee/{id}
Request Type: GET
Request Payload(Body): NA

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


public class TC002_Get_Single_Employee_Record extends ExtReport{

	
	@BeforeClass
	void getEmployeeData() throws InterruptedException
	{
		logger.info("*********Started TC002_Get_Single_Employee_Record **********");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employee/"+empID);
		
		Thread.sleep(7000);
	}


	
	@Test
	public void TC002_Get_Single_Employee_Recrd()
	{
		ExtReport sb = new ExtReport();
		
		logger.info("*********check Respose Body**********");
		sb.createNode("check Respose Body");
		
		String responseBody = response.getBody().asString();
		sb.assertTr(responseBody.contains(empID), "Respose Body contains(empID)");
		
	
		logger.info("*********check Status Code**********");
		sb.createNode("Checking Status Code");
		
		int statusCode = response.getStatusCode(); // Getting status code
		sb.assertEqual(Integer.toString(statusCode), "200","statusCode");
		
	
		logger.info("*********check Respose Time**********");
		sb.createNode("Checking Response Time");
		
		long responseTime = response.getTime(); // Getting status Line
		sb.assertTr(responseTime<6000,"responseTime<6000");
		
		
	
		logger.info("*********check status Line**********");
		sb.createNode("Checking Status Line");
		
		String statusLine = response.getStatusLine(); // Getting status Line
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
		logger.info("*********  Finished TC002_Get_Single_Employee_Record **********");
	}
	
}
