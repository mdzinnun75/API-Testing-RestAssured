package TestCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TA0005_PATCH_partialUpdateBooking {

	@Test
	void rest() {

		// specify the base url
		RestAssured.baseURI="https://restful-booker.herokuapp.com/";
		
		// Request object
		RequestSpecification httpRequest= RestAssured.given();
		
		// Request payload sending along with post request
		JSONObject jsonBooking = new JSONObject();	

		jsonBooking.put("totalprice", 326);
		jsonBooking.put("depositpaid", true);

		
		// setting cookie for authenticaion as per API Documentation
		
		httpRequest.header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=");
		httpRequest.header("Content-Type", "application/json; charset=utf-8");
		httpRequest.body(jsonBooking.toJSONString());
		
		// Response object
		Response response=httpRequest.request(Method.PATCH, "/booking/39");
		
		
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
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
		
 
		
		//String contentType=response.contentType();
		//System.out.println(contentType);
		System.out.println("=========================*=========================");
		Headers headers=response.getHeaders();
		System.out.println("Headers:"+headers);
		
		
		// success message verification
		String successCode= response.jsonPath().get("message");
		System.out.println("Success Code: " +successCode);
		//Assert.assertEquals(successCode, "Successfully! Record has been added.");
		
		
	}
}
