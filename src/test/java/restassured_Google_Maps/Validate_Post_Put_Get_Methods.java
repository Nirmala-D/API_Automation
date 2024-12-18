package restassured_Google_Maps;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import files.Payload;


public class Validate_Post_Put_Get_Methods {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
					
					// validate post method
					RestAssured.baseURI= "https://rahulshettyacademy.com";
					String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
					.body(Payload.AddPlace()).when().post("maps/api/place/add/json")
					.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
					.header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
					
					System.out.println(response);
					
					//for parsing Json using JsonPath class
					
					JsonPath js=new JsonPath(response); 
					String placeId=js.getString("place_id");
					
					System.out.println(placeId);
					
					    //validate put method (Update Place)
					
					String newAddress = "Summer Walk, Africa";
					
					given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
					.body("{\r\n" + 
							"\"place_id\":\""+placeId+"\",\r\n" + 
							"\"address\":\""+newAddress+"\",\r\n" + 
							"\"key\":\"qaclick123\"\r\n" + 
							"}").
					when().put("maps/api/place/update/json")
					.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
					
					//validate Get method (Get Place)
					
				String getPlaceResponse=	given().log().all().queryParam("key", "qaclick123")
					.queryParam("place_id",placeId)
					.when().get("maps/api/place/get/json")
					.then().assertThat().log().all().statusCode(200).extract().response().asString();
				JsonPath js1=ReUsableMethods.rawToJson(getPlaceResponse);
				String actualAddress =js1.getString("address");
				System.out.println(actualAddress);
				Assert.assertEquals(actualAddress, newAddress);
				
				
				
					
				}

			


	}


