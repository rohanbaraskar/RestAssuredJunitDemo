package sampleTest;

import org.apache.http.HttpRequest;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.XML;
import static org.hamcrest.Matchers.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class FirstTest {
	public static Response response;
    public static String jsonAsString;

	@BeforeClass
    public static void setupURL()
    {
        // here we setup the default URL and API base path to use throughout the tests
       baseURI = "http://localhost";
		port = 8080;
        basePath = "/landlords";
    }
	/*
	@Test
	public void helloworld(){
		
		 given().
      
		 when().
         get("/all").
         then().
 contentType(ContentType.JSON).
 extract().response();     
		jsonAsString = response.asString();	
		System.out.println(jsonAsString);
		
	}
	
	*/
	@Test
	public void testGet() throws URISyntaxException{
		
		URI uri = new URI("http://localhost:8080/landlords");
		
		Response response=
		given()
		.accept(ContentType.JSON)
		.when().get();
		
		System.out.println(response.asString());
		
		
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testResponseCode() throws URISyntaxException{
		
		//URI uri = new URI("http://localhost:8080/landlords");
		/*
		int code =
		
		given()
		.accept(ContentType.JSON)
		.when().get(uri)
		.thenReturn()
		.statusCode();
		
		System.out.println(code);
		
		Assert.assertEquals(HttpStatus.SC_OK, code);
		*/
		given()
		.accept(ContentType.JSON)
		.when().get()
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
		
		// imp
		// if u want to capture the response then use .thenReturn() method
		// if u want to validate then use .then() method
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetWithID() throws URISyntaxException{
		
		//URI uri = new URI("http://localhost:8080/landlords");
	/*
		String body =
		
		given()
		.accept(ContentType.JSON)
		.when().get("/8xRf8pGv")
		.thenReturn()
		.body()
		.asString();
		
		System.out.println(body);
		
		*/
		
		
		given()
		.accept(ContentType.JSON)
		.when().get("/8xRf8pGv")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
	
		// imp
		// if u want to capture the response then use .thenReturn() method
		// if u want to validate then use .then() method
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetWithHeaders() throws URISyntaxException{
		
		URI uri = new URI("https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA");
	Map<String,String> headers = new HashMap<String,String>();
	
	headers.put("key", "AIzaSyDvh8FDIB1WJ4mEPsLgfk3Kipa2NKhc66E");
	headers.put("Accept", "application/json");
		
		
		given()
		.headers(headers)
		.when().get(uri)
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
	
		String body =	given()
		.headers(headers)
		.when().get(uri)
		.thenReturn()
		.body()
		.asString();
		
		System.out.println(body);
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testValidateResposeFields() throws URISyntaxException{
		
		URI uri = new URI("https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA");
	Map<String,String> headers = new HashMap<String,String>();
	
	headers.put("key", "AIzaSyDvh8FDIB1WJ4mEPsLgfk3Kipa2NKhc66E");
	headers.put("Accept", "application/json");
		
		
		given()
		.headers(headers)
		.when().get(uri)
		.then()
		.body("status", equalToIgnoringCase("OK"));
		
		// this will validate fields on the main body
		// not the fields in internal structures, inside tags
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testValidateNestedResposeFieldsInList() throws URISyntaxException{
		
		URI uri = new URI("https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA");
	Map<String,String> headers = new HashMap<String,String>();
	
	headers.put("key", "AIzaSyDvh8FDIB1WJ4mEPsLgfk3Kipa2NKhc66E");
	headers.put("Accept", "application/json");
		
		
		given()
		.headers(headers)
		.when().get(uri)
		.then()
		.body("results.formatted_address", contains("Google Bldg 41, 1600 Amphitheatre Pkwy, Mountain View, CA 94043, USA"));
		
	
		// note: the nested tags
		//for a tag having multiple values as list use hasItem() method )
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testJsonPath() throws URISyntaxException{
		
		URI uri = new URI("https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA");
	Map<String,String> headers = new HashMap<String,String>();
	
	headers.put("key", "AIzaSyDvh8FDIB1WJ4mEPsLgfk3Kipa2NKhc66E");
	headers.put("Accept", "application/json");
		
	String s =	
		given()
		.headers(headers)
		.when().get(uri)
		.thenReturn()
		.asString();
	
	JsonPath json = new JsonPath(s);
		
	Assert.assertEquals("[ROOFTOP]", json.getString("results.geometry.location_type"));
	
		// note: the nested tags
		//for a tag having multiple values as list use hasItem() method )
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testParameteres() throws URISyntaxException{
		
		URI uri = new URI("https://maps.googleapis.com/maps/api/geocode/json");
	Map<String,String> headers = new HashMap<String,String>();
	
	
	headers.put("Accept", "application/json");
	/*	
	String s =	
		given()
		.headers(headers)
		.param("address", "1600+Amphitheatre+Parkway,+Mountain+View,+CA")
		.when().get(uri)
		.thenReturn()
		.asString();
	
	System.out.println(s);
	*/
	
			given()
			.headers(headers)
			.param("address", "1600+Amphitheatre+Parkway,+Mountain+View,+CA")
			.when().get(uri)
			.then()
			.statusCode(HttpStatus.SC_OK);
		
		
	
	
	
		// note: the nested tags
		//for a tag having multiple values as list use hasItem() method )
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testPost() throws URISyntaxException{
		
		URI uri = new URI("http://localhost:8080/landlords");
	Map<String,String> headers = new HashMap<String,String>();
	
	
	headers.put("Accept", "application/json");
	/*	
	String s =	
		given()
		.headers(headers)
		.param("address", "1600+Amphitheatre+Parkway,+Mountain+View,+CA")
		.when().get(uri)
		.thenReturn()
		.asString();
	
	System.out.println(s);
	*/
	Map<String, Object>  jsonAsMap = new HashMap<>();
	jsonAsMap.put("firstName", "aniketwarewa");
	jsonAsMap.put("lastName", "baraskar");
	jsonAsMap.put("trusted", false);
	

		/*
			Response r = given()
					.contentType("application/json")
					 .body(jsonAsMap).
			        when().
			        post("");

			    	String body = r.getBody().asString();
			    	System.out.println(body);
	
			    */	
			    	given()
							.contentType("application/json")
							 .body(jsonAsMap).
					        when().
					        post("").then().
					        statusCode(201);

					    	
		// note: the nested tags
		//for a tag having multiple values as list use hasItem() method )
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testPut() throws URISyntaxException{
		
		URI uri = new URI("http://localhost:8080/landlords");
	Map<String,String> headers = new HashMap<String,String>();
	
	
	headers.put("Accept", "application/json");
	/*	
	String s =	
		given()
		.headers(headers)
		.param("address", "1600+Amphitheatre+Parkway,+Mountain+View,+CA")
		.when().get(uri)
		.thenReturn()
		.asString();
	
	System.out.println(s);
	*/
	Map<String, Object>  jsonAsMap = new HashMap<>();
	jsonAsMap.put("firstName", "rohanput");
	jsonAsMap.put("lastName", "putdata");
	jsonAsMap.put("trusted", false);
	

		/*
			Response r = given()
					.contentType("application/json")
					 .body(jsonAsMap).
			        when().
			        post("");

			    	String body = r.getBody().asString();
			    	System.out.println(body);
	
			    */	
			    	given()
							.contentType("application/json")
							 .body(jsonAsMap).
					        when().
					        put("/0JJMBIe9").then().
					        statusCode(200);

					    	
		// note: the nested tags
		//for a tag having multiple values as list use hasItem() method )
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testDelete() throws URISyntaxException{
		
		URI uri = new URI("http://localhost:8080/landlords");
	Map<String,String> headers = new HashMap<String,String>();
	
	
	headers.put("Accept", "application/json");
	/*	
	
	
	

		/*
			Response r = given()
					.contentType("application/json")
					 .body(jsonAsMap).
			        when().
			        post("");

			    	String body = r.getBody().asString();
			    	System.out.println(body);
	
			    */	
			    	given()
							.contentType("application/json").
							 
					        when().
					        delete("/0JJMBIe9").then().
					        statusCode(200);

					    	
		// note: the nested tags
		//for a tag having multiple values as list use hasItem() method )
	}
}
