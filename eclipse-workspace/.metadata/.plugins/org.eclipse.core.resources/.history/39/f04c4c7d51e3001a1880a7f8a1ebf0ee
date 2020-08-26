package com.assignmentOne.junit;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.assignmentOne.testbase.testbase;

import net.serenitybdd.junit.runners.*;
import net.thucydides.core.annotations.Title;
import io.restassured.RestAssured;
import io.restassured.response.ResponseBody;

@RunWith(SerenityRunner.class)

public class FirstSerenityTest extends testbase {

	@Title("Test to check for country name India and verify response has Republic of India ")
	@Test
	public void getIndia() {
		RestAssured.given().when().get("/india").then().log().all().statusCode(200);

		io.restassured.response.Response response = RestAssured.given().when().
				get("/india").then().extract()
				.response();
		ResponseBody<?> body = response.getBody();
		// Get Response Body as String
		String bodyStringValue = body.asString();
		System.out.println(bodyStringValue);
		// Validate if Response Body Contains specific String
		// as URL given in the AssignmentOne is not working testing different URL
		// Checking the response code is 200 and response has email id
		// "janet.weaver@reqres.in
			Assert.assertTrue(bodyStringValue.contains("Republic of India"));

	}
	@Title("Test for Country name XYZ Statuss code 404 and verify the Response has Not Found ")
	@Test
	public void getXYZ() {

		RestAssured.given().when().get("/XYZ").then().statusCode(404);
		io.restassured.response.Response response = RestAssured.given().when().get("/xyz").then().log().all().extract()
				.response();
		ResponseBody<?> body = response.getBody();
		System.out.println(body);
		/*
		 * List<Object> em=body.jsonPath().getList("data.email");
		 * System.out.println("watch out!!!!!!!!"); System.out.println(em.toString());
		 */
		
		// Get Response Body as String
		String bodyStringValue = body.asString();
		System.out.println(bodyStringValue);
		Assert.assertTrue(bodyStringValue.contains("Not Found"));
	}
}
