package com.restfulbooker.base;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import com.restfulbooker.utilities.randomGenerator;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class BaseClass {


	public static RequestSpecification httpRequest;
	public static Response response;
	public static Logger log;
	
	public static int bookingId=669;

	@BeforeClass
	public void setup() {
		log= Logger.getLogger("RestfulBooker");
		PropertyConfigurator.configure("log4j.properties");
		log.setLevel(Level.DEBUG);


	}

}
