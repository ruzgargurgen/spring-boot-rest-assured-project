package com.tr.restassured.utils;

public class FactoryUtils {

	public static double factorial(Integer value) {
		if (value==0)return 1;
		
		return value*factorial(value-1);
	}

	
	
}
