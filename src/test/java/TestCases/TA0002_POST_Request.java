package TestCases;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;


public class TA0002_POST_Request {
	
	@Test
	void rest() {

		
		// specify the base url
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		
		// Request object
		RequestSpecification httpRequest= RestAssured.given();
		
		// Request payload sending along with post request
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "Naeem");
		requestParams.put("salary", "123");
		requestParams.put("age", "23");
		requestParams.put("id", 25);

		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		
		// Response object
		Response response=httpRequest.request(Method.POST, "/create");
		
		
		// Print response in the console window
		String responseBody= response.getBody().asString();
		System.out.println("Response Body is: "+ responseBody);
			
		// Status code validation 
		int statusCode= response.getStatusCode();
		System.out.println("Status code is: "+ statusCode);
		Assert.assertEquals(statusCode, 200);
		
		// Status line verification
		String statusline=response.getStatusLine();
		System.out.println("Status Line: "+ statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
		
		// Validating Headers
		String contentType=response.header("Content-Type");
		System.out.println("Content Type: "+ contentType);
		Assert.assertEquals(contentType, "application/json");
		
		String server=response.header("Server");
		System.out.println("Server name: "+ server);
		Assert.assertEquals(server, "nginx");
		
		//String contentType=response.contentType();
		//System.out.println(contentType);
		Headers headers=response.getHeaders();
		System.out.println("Headers:"+headers);
		
		
		// success message verification
		String successCode= response.jsonPath().get("message");
		System.out.println(successCode);
		Assert.assertEquals(successCode, "Successfully! Record has been added.");
		
		
	}
	
}


