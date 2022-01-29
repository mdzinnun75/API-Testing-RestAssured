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


public class TA0005_DataDrivenTest_AddNewEmployee {
	
	@DataProvider(name="empDataProvider")
	String[][] getEmpData() throws IOException{
		
		String filePath= System.getProperty("user.dir")+"/src/test/java/utilities/Activities_Data.xlsx";
		
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
	void postNewEmployees(String id, String title, String dueDate, String completion) {
		
		// specify the base url
		RestAssured.baseURI="https://fakerestapi.azurewebsites.net/api/v1";
		
		// Request object
		RequestSpecification httpRequest= RestAssured.given();
		
		// Request payload sending along with post request
		JSONObject requestParams = new JSONObject();
		requestParams.put("id", Integer.valueOf(id));
		requestParams.put("title", title);
		requestParams.put("dueDate", dueDate);
		requestParams.put("completed", Boolean.valueOf(completion));

		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		
		// Response object
		Response response=httpRequest.request(Method.POST, "/Activities");
		
		// capture response body to perform validation
		String responseBody= response.getBody().asString();
		System.out.println("Response Body is: "+ responseBody);
		Assert.assertEquals(responseBody.contains(title), true);
		Assert.assertEquals(responseBody.contains(id), true);
		Assert.assertEquals(responseBody.contains(id), true);
	}
}
