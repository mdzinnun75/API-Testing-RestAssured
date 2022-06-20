package com.restfulbooker.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONObject;
import org.testng.Assert;
import com.restfulbooker.base.BaseClass;
import com.restfulbooker.utilities.randomGenerator;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TA0004_PUT_updateBooking extends BaseClass{

	String firstName;
	
	
	@Epic("EP001")
	@Feature("Update a Booking")
	@Step("#1 Defining Request & Response object, Sending User Details")
	@Severity(SeverityLevel.BLOCKER)
	@BeforeClass
	void getAllBookingIDS() {
	
	log.info("========================<<|TA0004_PUT_updateBooking|>>========================");

		// specify the base url
		RestAssured.baseURI="https://restful-booker.herokuapp.com/";

		// Request object
		httpRequest= RestAssured.given();

	log.info("==========================| sending request payload |=========================");

		// Request payload sending along with post request
		JSONObject jsonBooking = new JSONObject();
		firstName=randomGenerator.randomName();
		jsonBooking.put("firstname", firstName);
		jsonBooking.put("lastname", randomGenerator.randomName());
		jsonBooking.put("totalprice", randomGenerator.randomPrice());
		jsonBooking.put("depositpaid", randomGenerator.randomBoolean());
		jsonBooking.put("additionalneeds", randomGenerator.randomAdditionalNeeds());

			JSONObject jsonBookingDates = new JSONObject();
			LocalDate lcd=  randomGenerator.randomDate1();
			String date1=lcd.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			jsonBookingDates.put("checkin", date1);
			
			jsonBookingDates.put("checkout", randomGenerator.randomDate2(lcd));
		jsonBooking.put("bookingdates", jsonBookingDates);

		// setting cookie for authentication as per API Documentation

		httpRequest.header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=");
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(jsonBooking.toJSONString());

		// Response object
		response=httpRequest.request(Method.PUT, "/booking/"+bookingId);

	}
	
	
	@Epic("EP001")
	@Feature("Update a Booking")
	@Step("#2 Checking Response Body")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	void checkResponseBody(){

	log.info("==========================| checking response body |=========================");
		
		// Print response in the console window
		String responseBody= response.getBody().asString();
		log.info("Response Body --> "+ responseBody);
		Assert.assertTrue(responseBody.contains(firstName));
	}
	
	
	
	@Epic("EP001")
	@Feature("Update a Booking")
	@Step("#3 Checking Status Code")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	void checkStatusCode() {
	log.info("==========================| checking status code |==========================");

		// Status code validation
		int statusCode= response.getStatusCode();
		log.info("Status code -->"+ statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	
	
	@Epic("EP001")
	@Feature("Update a Booking")
	@Step("#4 Checking Status Line")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	void checkStatusLine() {
		
	log.info("==========================| checking status line |==========================");

		// Status line verification
		String statusline=response.getStatusLine();
		log.info("Status Line --> "+ statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
	}
	
	
	@Epic("EP001")
	@Feature("Update a Booking")
	@Step("#5 Checking Response Time")
	@Severity(SeverityLevel.NORMAL)
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
	
	
	@Epic("EP001")
	@Feature("Update a Booking")
	@Step("#6 Checking Content Encoding")
	@Severity(SeverityLevel.NORMAL)
	@Test
	void checkContentEncoding() {
		
	log.info("==========================| checking content type |==========================");
		
		// Content Type validation
		String contentType=response.header("Content-Type");
		log.info("Content Type --> "+ contentType);
		Assert.assertEquals(true, contentType.contains("application/json"));
	}

	
	
	@Epic("EP001")
	@Feature("Update a Booking")
	@Step("#7 Checking Server Name")
	@Severity(SeverityLevel.NORMAL)
	@Test
	void checkServer() {
		
	log.info("==========================| checking response server |==========================");
	
		String server=response.header("Server");
		log.info("Server name --> "+ server);
		Assert.assertEquals(server, "Cowboy");
	}
	
	@Epic("EP001")
	@Feature("Update a Booking")
	@Step("#8 Getting All the Headers")
	@Severity(SeverityLevel.MINOR)
	@Test
	void getAllHeaders() {
	log.info("==========================| checking all the headers|==========================");

		// get all the headers
		Headers headers=response.getHeaders();
		
		for(Header header: headers) {
			System.out.println(header.getName()+"   "+header.getValue());
	
		}
	}

	
	
	@Epic("EP001")
	@Feature("Update a Booking")
	@Step("#9 Tear Down")
	@Test
	void tearDown() {
		log.info("========================<<|Finished TA0004_PUT_updateBooking|>>========================");
	}
}




