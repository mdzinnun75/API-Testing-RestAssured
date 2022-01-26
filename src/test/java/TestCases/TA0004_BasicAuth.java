package TestCases;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TA0004_BasicAuth {

	public static void main(String[] args) {
		
		// specify the base url
		RestAssured.baseURI="http://restapi.demoqa.com/authentication/CheckForAuthentication";
		
		// Basic authenticaton -> neccessary to set before sending the request.
		PreemptiveBasicAuthScheme authscheme = new PreemptiveBasicAuthScheme();
		authscheme.setUserName("someone");
		authscheme.setPassword("656565");
		
		RestAssured.authentication=authscheme;
		
		// Request object
		RequestSpecification httpRequest= RestAssured.given();
		
		// Response object
		Response response= httpRequest.request(Method.GET, "/");
		
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

