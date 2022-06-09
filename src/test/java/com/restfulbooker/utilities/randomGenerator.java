package com.restfulbooker.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class randomGenerator {
	

	public static String randomName() {
		
		 String[] names = {"Fred", "Jane", "Richard", "John", "Smith", 
				"Jonny", "William", "Michael", "Joseph", "Thomas","Brown", "Moore"
						+ "Wilson","Anderson", "Clark" };
		Random rd = new Random();
		int index = rd.nextInt(names.length);
		String name=(names[index]);
		return name;
	}
	
	public static boolean randomBoolean() {
		
		 Random rd = new Random(); 
	     return rd.nextBoolean(); 
		
	}
	
	
	public static int randomPrice() {
		
		 Random rd = new Random(); 
	     return rd.nextInt(350);
	}
	
	
	public static String randomAdditionalNeeds() {
		String[] additionalNeeds = {"Breakfast", "Lunch", "Dinner", "Desert", "Asian Meal", 
									"Spaghetti", "Soup", "Tea", "Coffee" };
		Random rd = new Random();
		int index = rd.nextInt(additionalNeeds.length);
		String additionalNeed=(additionalNeeds[index]);
		return additionalNeed;
	     
	}
	
	public static LocalDate randomDate1() {
		
		LocalDate from = LocalDate.of(2022, 6, 1);
		LocalDate to = LocalDate.of(2023, 3, 1);
		long days = from.until(to, ChronoUnit.DAYS);	// --> Total days
	//	System.out.println(days); 
		
		long randomDays = ThreadLocalRandom.current().nextLong(days + 1);	// --> random day within max value
	//	Change 'days + 1' to 'days' if randomly generating 01/01/2017 i.e. the end date is exclusive.
	//	System.out.println(randomDays); 
		
		LocalDate randomDate1 = from.plusDays(randomDays);
	//	String date1=randomDate1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));	// formatting to a certain format

		return randomDate1;
	     
	}
	
	
	
	public static String randomDate2(LocalDate localDate) { //
			
		LocalDate randomDate2 = localDate.plusDays(60);
	//	System.out.println(randomDate2.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		String date2= randomDate2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		return date2;

	     
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
