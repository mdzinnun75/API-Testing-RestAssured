package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TA0003_GET_Request {

	@Test
	void getLocationDetails()
	{
		// specify the base url
		RestAssured.baseURI="https://maps.googleapis.com";
		
		// Request object
		RequestSpecification httpRequest= RestAssured.given();
		
		// Response object
		Response response= httpRequest.request(Method.GET, "/maps/api/place/nearbysearch/xml?"
				+ "location=-33.8670522,151.1957362&radius=1500&type=supermarket&"
				+ "key=AlzaSyBjGCE3VpLU4lgTqSTDmHmj2HoELb4jy1s");
						
				
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
	}
}
