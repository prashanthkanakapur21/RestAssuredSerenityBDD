package com.assignmentThree.testbase;

import org.junit.BeforeClass;

import io.restassured.RestAssured;

public class testbase 
{
	@BeforeClass
	public static void init() {
	RestAssured.baseURI="https://postman-echo.com/basic-auth";

	}


}
