package com.assignmentOne.junit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.assignmentOne.testbase.testbase;

import net.serenitybdd.junit.runners.*;
import net.thucydides.core.annotations.Title;
import io.restassured.RestAssured;
import io.restassured.response.ResponseBody;

@RunWith(SerenityRunner.class)

public class Countries extends testbase {

	@Title("Test to check for country name Norway capital is Oslo and print response")
	@Test
	public void getNorway() {

		// get request to service using
		// restassured:http://restcountries.eu/rest/v1/name/norway

		RestAssured.given().
					when().get("/Norway").
					then().log().all().statusCode(200);

		//Validate that the capital is Oslo and Print the details of Norway on command line
		io.restassured.response.Response response = RestAssured.given().
											        when().get("/norway").then().extract().response();
		ResponseBody<?> body = response.getBody();
		String bodyStringValue = body.asString();
		System.out.println(bodyStringValue);
		System.out.println("\"capital\":\"Oslo\"");

		Assert.assertTrue(bodyStringValue.contains("\"capital\":\"Oslo\""));

	}

}
