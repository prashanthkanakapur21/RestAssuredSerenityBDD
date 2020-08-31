package com.assignmentThree.junit;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.assignmentThree.testbase.testbase;
import com.assignmentThree.utils.TestUtils;
import com.serenityrestassured.model.studentClass;

import net.serenitybdd.junit.runners.*;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FinalAssignmentTest extends testbase 
{
	static String firstName = "TestStudent1";
	private String lastName = TestUtils.getRandomValue() +"TestLast";
	private String email = TestUtils.getRandomValue() + "Test123@qwerty.com";
	private String programme = "Electronics";

	private String Username = "postman";
	private String Password = "password";
//AssignmentOne
	@Title("Test to check for country name India and verify response has Republic of India ")
	@Test
	public void getIndia() {
		RestAssured.given().when().get("https://restcountries.eu/rest/v2/name/india").then().log().all().statusCode(200);

		io.restassured.response.Response response = RestAssured.given().when().
				get("https://restcountries.eu/rest/v2/name/india").then().extract()
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

		RestAssured.given().when().get("https://restcountries.eu/rest/v2/name/XYZ").then().statusCode(404);
		io.restassured.response.Response response = RestAssured.given().when().get("https://restcountries.eu/rest/v2/name/xyz").then().log().all().extract()
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
//AssignmentFour
	@Title("Test for Successful Authentication")
	@Test
	public void Authentication001() {

		// 1.Validate that post request is successful and status code is 200 and
		System.out.println("Successful authentication!!!!!");

		ValidatableResponse statusCode = SerenityRest.given().auth().preemptive().basic(Username, Password).when().get("https://postman-echo.com/basic-auth")
				.then().log().all().statusCode(200);
		System.out.println("The status code is:" + statusCode);

	}

	@Title("Test for UnSuccessful Authentication: Invalid creds")
	@Test
	public void Authentication002() {

		// Passing the invalid creds to get Error
		System.out.println("Authentication Failed!!!!");

		ValidatableResponse statusCode = SerenityRest.given().auth().preemptive().basic("Username", "Invalid").when()
				.get("https://postman-echo.com/basic-auth").then().log().all().statusCode(401);
		System.out.println("The status code is:" + statusCode);
	}
	
//AssignmentThree
	@Title("Test to create new student")
	@Test
	public void createStudent1() {
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("LogicDesign");
		courses.add("VHDL");

		studentClass student = new studentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);

		// 1.Validate that post request is successful and status code is 201 and
		// 2. storing the response in response variable to verify
		System.out.println("Details!!!!!!!!!!!");
		System.out.println(student);
		String response = SerenityRest.given().contentType(ContentType.JSON).log().all().when().body(student).post("http://localhost:8080/student")
				.then().log().all().assertThat().statusCode(201).extract().path("msg");
		System.out.println(response);
		if (response.compareToIgnoreCase("student added") == 0)
			System.out.println("msg: Student added is prestn in response: Success!!");
		else
			System.out.println("Failure!!!");

	}

	@Title("Trying to create student again with same data:Error Scenario")
	@Test
	public void createStudent2() {
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("LogicDesign");
		courses.add("VHDL");

		studentClass student = new studentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		//Passing the duplicate Email id to get Error
		student.setEmail("vestibulum@ridiculusmus.edu");
		student.setProgramme(programme);
		student.setCourses(courses);
		SerenityRest.given().contentType(ContentType.JSON).when().body(student).post("http://localhost:8080/student").then().log().all()
				.statusCode(500);
	}
//AssignmentTwo
	@Title("Test to check for country name Norway capital is Oslo and print response")
	@Test
	public void getNorway() {

		// get request to service using
		// restassured:http://restcountries.eu/rest/v1/name/norway

		RestAssured.given().
					when().get("https://restcountries.eu/rest/v2/name/Norway").
					then().log().all().statusCode(200);

		//Validate that the capital is Oslo and Print the details of Norway on command line
		io.restassured.response.Response response = RestAssured.given().
											        when().get("https://restcountries.eu/rest/v2/name/norway").then().extract().response();
		ResponseBody<?> body = response.getBody();
		String bodyStringValue = body.asString();
		System.out.println(bodyStringValue);
		System.out.println("\"capital\":\"Oslo\"");

		Assert.assertTrue(bodyStringValue.contains("\"capital\":\"Oslo\""));

	}


}
