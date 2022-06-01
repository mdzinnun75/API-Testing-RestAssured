package TestCases;

import java.io.IOException;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.XLUtils;


public class TC0007_DataDrivenTest_AddNewEmployee {
	
	@DataProvider(name="empDataProvider")
	String[][] getEmpData() throws IOException{
		
		String filePath= System.getProperty("user.dir")+"/src/test/java/utilities/Booking_Details.xlsx";
		
		// getting row & column number
		int rowNum=XLUtils.getRowCount(filePath, "Sheet1"); System.out.println(rowNum);
		int columnNum= XLUtils.getCellcount(filePath, "Sheet1", 1); System.out.println(columnNum);
		
		// creating an String array 
		String[][] empData= new String[rowNum][columnNum];
		
		for (int i = 1; i <= rowNum; i++) {
			for (int j = 0; j < columnNum; j++) {
				System.out.println("Row: "+i+" "+"Column: "+j);
				empData[i-1][j]= XLUtils.getCellData(filePath, "Sheet1", i, j);
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
		RequestSpecification httpRequest= RestAssured.given();
		
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

		
		httpRequest.header("Content-Type", "application/json; charset=utf-8");
		httpRequest.body(jsonBooking.toJSONString());
		
		// Response object
		Response response=httpRequest.request(Method.POST, "/booking");
		
		// capture response body to perform validation
		String responseBody= response.getBody().asString();
		System.out.println("Response Body is: "+ responseBody);
		Assert.assertEquals(responseBody.contains(firstname), true);
		Assert.assertEquals(responseBody.contains(lastname), true);
		
		Thread.sleep(3000);
		
	}
}
