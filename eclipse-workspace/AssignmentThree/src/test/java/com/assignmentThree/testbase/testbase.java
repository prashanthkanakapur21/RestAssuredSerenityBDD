package com.assignmentThree.testbase;

import org.junit.BeforeClass;

import io.restassured.RestAssured;

public class testbase 
{
	@BeforeClass
	public static void init() {
	RestAssured.baseURI="http://localhost:8085/student";

	}


}