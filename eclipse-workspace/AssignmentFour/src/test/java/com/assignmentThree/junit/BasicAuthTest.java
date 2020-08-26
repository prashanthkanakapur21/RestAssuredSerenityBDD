package com.assignmentThree.junit;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.assignmentThree.testbase.testbase;
import net.serenitybdd.junit.runners.*;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import io.restassured.response.ValidatableResponse;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BasicAuthTest extends testbase {

	private String Username = "postman";
	private String Password = "password";

	@Title("Test for Successful Authentication")
	@Test
	public void Authentication001() {

		// 1.Validate that post request is successful and status code is 200 and
		System.out.println("Successful authentication!!!!!");

		ValidatableResponse statusCode = SerenityRest.given().auth().preemptive().basic(Username, Password).when().get()
				.then().log().all().statusCode(200);
		System.out.println("The status code is:" + statusCode);

	}

	@Title("Test for UnSuccessful Authentication: Invalid creds")
	@Test
	public void Authentication002() {

		// Passing the invalid creds to get Error
		System.out.println("Authentication Failed!!!!");

		ValidatableResponse statusCode = SerenityRest.given().auth().preemptive().basic("Username", "Invalid").when()
				.get().then().log().all().statusCode(401);
		System.out.println("The status code is:" + statusCode);
	}

}
