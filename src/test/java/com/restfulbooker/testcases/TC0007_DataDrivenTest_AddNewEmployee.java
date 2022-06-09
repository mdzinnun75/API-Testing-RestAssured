package com.restfulbooker.testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.restfulbooker.base.BaseClass;
import com.restfulbooker.utilities.XLUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TC0007_DataDrivenTest_AddNewEmployee extends BaseClass{

	@DataProvider(name="empDataProvider")
	String[][] getEmpData() throws IOException{

		String filePath= System.getProperty("user.dir")+"/src/test/java/com/restfulbooker/utilities/Booking_Details.xlsx";

		// getting row & column number
		int rowNum=XLUtils.getRowCount(filePath, "Sheet1"); System.out.println(rowNum);
		int columnNum= XLUtils.getCellcount(filePath, "Sheet1", 1); System.out.println(columnNum);

		// creating an String array
		String[][] empData= new String[rowNum][columnNum];

		for (int row = 1; row <= rowNum; row++) {
			for (int column = 0; column < columnNum; column++) {
				System.out.println("Row: "+row+" "+"Column: "+column);
				empData[row-1][column]= XLUtils.getCellData(filePath, "Sheet1", row, column);
			}
		}

		return empData;
	}




	@Test(dataProvider = "empDataProvider")
	void postNewEmployees(String firstname, String lastname, String totalprice,
							String depositpaid, String  checkin, String checkout,
							String additionalneeds) throws InterruptedException {

		// specify the base url
		RestAssured.baseURI="https://restful-booker.herokuapp.com/";

		// Request object
		httpRequest= RestAssured.given();

		// Request payload sending along with post request
		JSONObject jsonBooking = new JSONObject();
		jsonBooking.put("firstname", firstname);
		jsonBooking.put("lastname", lastname);
		jsonBooking.put("totalprice", Integer.valueOf(totalprice));
		jsonBooking.put("depositpaid", Boolean.valueOf(depositpaid));
		jsonBooking.put("additionalneeds", additionalneeds);

			JSONObject jsonBookingDates = new JSONObject();
			jsonBookingDates.put("checkin", checkin);
			jsonBookingDates.put("checkout", checkout);

		jsonBooking.put("bookingdates", jsonBookingDates);


		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(jsonBooking.toJSONString());

		// Response object
		response=httpRequest.request(Method.POST, "/booking");

		// capture response body to perform validation
		String responseBody= response.getBody().asString();
		System.out.println("Response Body is: "+ responseBody);
		Assert.assertEquals(responseBody.contains(firstname), true);
		Assert.assertEquals(responseBody.contains(lastname), true);
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");

		Thread.sleep(1000);

	}
}
