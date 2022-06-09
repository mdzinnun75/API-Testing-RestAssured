package com.restfulbooker.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.restfulbooker.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TA0001_GET_getAllBookings extends BaseClass {

	
	@BeforeClass
	void getAllBookingIDs() {
		
		log.info("========================<<|TA0001_GET_getAllBookings|>>========================");
		
		// specify the base url
		RestAssured.baseURI="https://restful-booker.herokuapp.com/";

		// Request object
		httpRequest= RestAssured.given();

		// Response object
		response= httpRequest.request(Method.GET, "/booking");
	}
	
	
	@Test
	void checkResponseBody(){

		log.info("==========================| checking response body |=========================");
		
		// Print response in the console window
		String responseBody= response.getBody().asString();
		log.info("Response Body --> "+ responseBody);
		Assert.assertTrue(responseBody != null);
	}
	
	
	@Test
	void checkStatusCode() {
		
		log.info("==========================| checking status code |==========================");
		
		// Status code validation
		int statusCode= response.getStatusCode();
		log.info("Status code --> "+ statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkStatusLine() {
		
		log.info("==========================| checking status line |==========================");

		// Status line verification
		String statusline=response.getStatusLine();
		log.info("Status Line --> "+ statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
	}
	

	@Test
	void checkResponseTime() {
		
	log.info("==========================| checking response time |==========================");
		
		// response time validation
		long responseTime= response.getTime();
		log.info("Response Time -->  "+ responseTime);
		
		if(responseTime>3000) log.warn("response time is greater than 3000");
		
		SoftAssert sa= new SoftAssert();
		sa.assertTrue((responseTime < 3000));
	}
	
	
	@Test
	void checkServer() {
		
		log.info("==========================| checking response server |==========================");
		
		// Server validation
		String server= response.header("Server");
		log.info("Server --> "+ server);
		Assert.assertEquals(server, "Cowboy");
	}
	
	
	@Test
	void checkContentEncoding() {
		
		log.info("==========================| checking content type |==========================");
		
		// Content Type validation
		String contentType= response.header("Content-Type");
		log.info("Content Encoding -->  "+ contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}
	

	@Test
	void getAllHeaders() {
		log.info("==========================| checking all the headers|==========================");

		// get all the headers
		Headers headers=response.getHeaders();
		//System.out.println("Headers:"+headers);

		for(Header header: headers) {
			log.info(header.getName()+"		"+header.getValue());
		}
	}

	
	@Test
	void tearDown() {
		log.info("========================<<|Finished TA0001_GET_getAllBookings|>>========================");
	}

}


