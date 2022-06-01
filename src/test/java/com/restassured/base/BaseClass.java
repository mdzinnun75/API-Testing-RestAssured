package com.restassured.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class BaseClass {

	
	public static RequestSpecification httpRequest;
	public static Response response;
	public String ID="656";
	public String firstName="";
	public String lastName="";
	public String checkIn="";
	public String checkOut="";
	
	public static Logger log;
	
	
	public void setup() {
		log= Logger.getLogger("Restful Bookers");
		PropertyConfigurator.configure("Log4j.properties");
		log.setLevel(Level.DEBUG);
		
	
	}
	
}
