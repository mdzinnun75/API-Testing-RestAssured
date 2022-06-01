package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TA0002_GET_getAllBookings {

	@Test
	void getAllBookingIDs(){
		// specify the base url
		RestAssured.baseURI="https://restful-booker.herokuapp.com/";
		
		// Request object
		RequestSpecification httpRequest= RestAssured.given();
		
		// Response object
		Response response= httpRequest.request(Method.GET, "/booking");
		
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
		
		// get all the headers
		Headers headers=response.getHeaders();
		//System.out.println("Headers:"+headers);
		
		for(Header header: headers) {
			System.out.println(header.getName()+"   "+header.getValue());
		}
	}
	

}
	

