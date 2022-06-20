package com.tr.com.tr.restassured;

import org.json.simple.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@SpringBootTest
class TestApiApplicationTests {
	
	    @BeforeTest
	    public static void setup() {
	    	baseURI = "http://localhost:3434";
	    }

	    @Test(priority = 0)
		public void sumRequest() {
			Response response=given()
					.header("username","ruzgar")
					.header("password","123")
					.contentType(ContentType.JSON)
					.queryParam("param", 4)
					.when()
					.get("/sum")
					.then()
					.extract().response();
			
			Assert.assertEquals(200,response.statusCode());
			Assert.assertEquals("{username:ruzgar ,password:123}", response.jsonPath().getString("user"));
			Assert.assertEquals("24.0", response.jsonPath().getString("result"));
		}
	    @Test(priority = 1)
	    public void addRequest() {
			Response response=given()
	        		.header("username", "ruzgar")
	        		.header("password", "123")
	        		.contentType(ContentType.JSON)
	        		.queryParam("params", "1","2","3")
	                .when()
	                .post("/add")
	                .then()
	                .extract().response();
	        
	        Assert.assertEquals(200, response.statusCode());
	        Assert.assertEquals("{username:ruzgar ,password:123}", response.jsonPath().getString("user"));
	        Assert.assertEquals("6.0", response.jsonPath().getString("result"));
	    }
	   
	    @Test(priority = 2)
	    public void divisionRequest() {
			Response response=given()
	        		.header("username", "ruzgar")
	        		.header("password", "123")
	        		.contentType(ContentType.JSON)
	        		.queryParam("params", "8","2","2")
	                .when()
	                .post("/division")
	                .then()
	                .extract().response();
	   
	        Assert.assertEquals(200, response.statusCode());
	        Assert.assertEquals("{username:ruzgar ,password:123}", response.jsonPath().getString("user"));
	        Assert.assertEquals("2.0", response.jsonPath().getString("result"));
	    }
	    @Test(priority = 3)
	    public void multiplicationRequest() {
			Response response=given()
	        		.header("username", "ruzgar")
	        		.header("password", "123")
	        		.contentType(ContentType.JSON)
	        		.queryParam("params", "3","3","5")
	                .when()
	                .post("/multiplication")
	                .then()
	                .extract()
	                .response();

	        Assert.assertEquals(200, response.statusCode());
	        Assert.assertEquals("{username:ruzgar ,password:123}", response.jsonPath().getString("user"));
	        Assert.assertEquals("45.0", response.jsonPath().getString("result"));
	    }
	   

	    @Test(priority = 4)
		public void postUserRequest() {
			JSONObject request=new JSONObject();
			request.put("userName", "test1");
			request.put("password", "123");
			Response response=given()
					.header("Content-Type","aplication/json")
					.accept(ContentType.JSON)
					.contentType(ContentType.JSON)
					.body(request.toJSONString())
					.when()
					.post("/users")
					.then()
					.extract()
					.response();
			
			Assert.assertEquals(200,response.statusCode());
			Assert.assertEquals("test1", response.jsonPath().getString("userName"));
			Assert.assertEquals("123", response.jsonPath().getString("password"));
		}
		
	    @Test(priority = 5)
		public void getAllUserRequest() {
			Response response=given()
					.header("Content-Type","aplication/json")
					.accept(ContentType.JSON)
					.contentType(ContentType.JSON)
					.when()
					.get("/getAll")
					.then()
					.extract()
					.response();
			
			Assert.assertEquals(200,response.statusCode());
		}
		
	    @Test(priority = 6)
		public void getUserByIdRequest() {
			Response response=given()
					.header("Content-Type","aplication/json")
					.accept(ContentType.JSON)
					.contentType(ContentType.JSON)
					.when()
					.get("getById/1")
					.then()
					.extract()
					.response();
			
			Assert.assertEquals(200,response.statusCode());
		}
	    
	    @Test(priority = 7)
		public void updateUserRequest() {
			JSONObject request=new JSONObject();
			request.put("userName", "test1");
			request.put("password", "12345");
			Response response=given()
					.header("Content-Type","aplication/json")
					.accept(ContentType.JSON)
					.contentType(ContentType.JSON)
					.body(request.toJSONString())
					.when()
					.put("/edit/1")
					.then()
					.extract()
					.response();
			
			Assert.assertEquals(200,response.statusCode());
			Assert.assertEquals("test2", response.jsonPath().getString("userName"));
			Assert.assertEquals("12345", response.jsonPath().getString("password"));
		}
		
	    @Test(priority = 8)
		public void deleteUserByIdRequest() {
			Response response=given()
					.when()
					.delete("delete/1")
					.then()
					.extract()
					.response();
			
			Assert.assertEquals(200,response.statusCode());
		}
		
	   
		
		
		
	   
	   

}
