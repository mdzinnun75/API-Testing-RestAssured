package TestCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TC0008_createToken {

	@Test
	void auth() {
		
		// specify the base url
		RestAssured.baseURI="https://restful-booker.herokuapp.com/";
		
		// Request object
		RequestSpecification httpRequest= RestAssured.given();
		
		JSONObject auth = new JSONObject();	
		auth.put("username", "admin");
		auth.put("password", "password123");
			
		// setting header as per API documentation
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(auth.toJSONString());
		
		// Response object
		Response response= httpRequest.request(Method.POST, "/auth");
		
		// Print response in the console window
		String responseBody= response.getBody().asString();
		System.out.println("Response Body is: "+ responseBody);
		//Assert.assertEquals();
		
		// Status code validation 
		int statusCode= response.getStatusCode();
		System.out.println("Status code is: "+ statusCode);
		Assert.assertEquals(statusCode, 200);
		
		// Status line verification
		String statusline=response.getStatusLine();
		System.out.println("Status Line: "+ statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");

	}
}

