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

public class TA0001_GET_Request {
	
	@Test
	void getBookingDetails()
	{
		// specify the base url
		RestAssured.baseURI="https://restful-booker.herokuapp.com/booking";
		
		// Request object
		RequestSpecification httpRequest= RestAssured.given();
		
		// Response object
		Response response= httpRequest.request(Method.GET, "/1");
		
		// Print response in the console window
		String responseBody= response.getBody().asString();
		System.out.println("Response Body is: "+ responseBody);
		Assert.assertEquals(responseBody.contains("firstname"), true);

		// getting every node of a JSON file
		JsonPath jsonpath= response.jsonPath();
		String nodeValue1= jsonpath.get("firstname");
		String nodeValue2= jsonpath.get("lastname");
		String nodeValue3= jsonpath.get("totalprice").toString();
		String nodeValue4= jsonpath.get("depositpaid").toString();
		String nodeValue5= jsonpath.get("bookingdates").toString();
		System.out.println(nodeValue1);
		System.out.println(nodeValue2);
		System.out.println(nodeValue3);
		System.out.println(nodeValue4);
		System.out.println(nodeValue5);
		
		//Assert.assertEquals(jsonpath.get("firstname"), "James"); // these values are not certain. depends on  the weather.
		
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

//https://gorest.co.in/