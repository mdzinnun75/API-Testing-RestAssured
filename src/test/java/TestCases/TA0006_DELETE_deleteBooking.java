package TestCases;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TA0006_DELETE_deleteBooking {
	
	@Test
	void getBookingDetails()
	{
		// specify the base url
		RestAssured.baseURI="https://restful-booker.herokuapp.com/";
		
		// Request object
		RequestSpecification httpRequest= RestAssured.given();
		
		// setting header as per API documentation
		httpRequest.header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=");
		httpRequest.header("Content-Type", "application/json; charset=utf-8");
		
		// Response object
		Response response= httpRequest.request(Method.DELETE, "booking/739");
		
		
		// Status code validation 
		int statusCode= response.getStatusCode();
		System.out.println("Status code is: "+ statusCode);
		Assert.assertEquals(statusCode, 201);
		
		// Status line verification
		String statusline=response.getStatusLine();
		System.out.println("Status Line: "+ statusline);
	//	Assert.assertEquals(statusline, "HTTP/1.1 201 Created");
		
		// get all the headers
		Headers headers=response.getHeaders();
		//System.out.println("Headers:"+headers);
		
		for(Header header: headers) {
			System.out.println(header.getName()+"   "+header.getValue());
		}
	}
	

}
