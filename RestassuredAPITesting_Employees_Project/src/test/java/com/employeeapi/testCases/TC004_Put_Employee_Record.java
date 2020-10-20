/******************************************************
Test Name:Update an employee record
URI: http://dummy.restapiexample.com/api/v1/update/{id}
Request Type: PUT
Request Payload(Body): {"name":"XXXXX","salary":"XXXX","age":"XX"}

********* Validations **********
Response Payload(Body) : {"name":"XXXXX","salary":"XXXX","age":"XX"}
Status Code : 200
Status Line : HTTP/1.1 200 OK
Content Type : text/html; charset=UTF-8
Server Type :  nginx/1.14.1
Content Encoding : gzip
**********************************************************/

package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.annotations.*;
import com.employeeapi.utilities.ExtReport;
import com.employeeapi.utilities.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;


public class TC004_Put_Employee_Record extends ExtReport  {

	String empName=RestUtils.empName();
	String empSalary=RestUtils.empSal();
	String empAge=RestUtils.empAge();
	
	
	@BeforeClass
	void updateEmployee() throws InterruptedException
	{
		logger.info("*********Started TC004_Put_Employee_Record **********");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();

		// JSONObject is a class that represents a simple JSON. We can add Key-Value pairs using the put method
		//{"name":"John123X","salary":"123","age":"23"}
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName); // Cast
		requestParams.put("salary", empSalary);
		requestParams.put("age", empAge);
		
		// Add a header stating the Request body is a JSON
		httpRequest.header("Content-Type", "application/json");

		// Add the Json to the body of the request
		httpRequest.body(requestParams.toJSONString());

		response = httpRequest.request(Method.PUT, "/update/"+empID);
		
		Thread.sleep(5000);

	}
	
	
	@Test
	public void TC004_Put_Employee_Recrd()
	{
		
		ExtReport sb = new ExtReport();
		
		
		logger.info("*********checkResposeBody **********");
		sb.createNode("checkResposeBody");
		
		String responseBody = response.getBody().asString();
		
		sb.assertTr(responseBody.contains(empName),"responseBody.contains(empName)");
		sb.assertTr(responseBody.contains(empSalary),"responseBody.contains(empSalary)");
		sb.assertTr(responseBody.contains(empAge),"responseBody.contains(empAge)");
		

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
		logger.info("*********  Finished TC004_Put_Employee_Record **********");
	}

}
