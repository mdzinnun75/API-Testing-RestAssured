package TestCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class TA0003_POST_createBooking {
	
	@Test
	void rest() {
		
		// specify the base url
		RestAssured.baseURI="https://restful-booker.herokuapp.com/";
		
		// Request object
		RequestSpecification httpRequest= RestAssured.given();
		
		// Request payload sending along with post request
		JSONObject jsonBooking = new JSONObject();	
		jsonBooking.put("firstname", "Tom");
		jsonBooking.put("lastname", "The Cat");
		jsonBooking.put("totalprice", 235);
		jsonBooking.put("depositpaid", true);
		jsonBooking.put("additionalneeds", "a BIG fish!");
			
			JSONObject jsonBookingDates = new JSONObject();
			jsonBookingDates.put("checkin", "2021-02-23");
			jsonBookingDates.put("checkout", "2020-10-23");

		jsonBooking.put("bookingdates", jsonBookingDates);
		

		httpRequest.body(jsonBooking.toJSONString());
		
		// setting cookie for authentication as per API Documentation
		httpRequest.header("Content-Type", "application/json; charset=utf-8");
		
		
		// Response object
		Response response=httpRequest.request(Method.POST, "/booking");
		
		
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
		Assert.assertEquals(true, contentType.contains("application/json"));
		
		String server=response.header("Server");
		System.out.println("Server name: "+ server);
		Assert.assertEquals(server, "Cowboy");
		
		//String contentType=response.contentType();
		//System.out.println(contentType);
		Headers headers=response.getHeaders();
		System.out.println("Headers:"+headers);
		
		
		
	}
	
}


