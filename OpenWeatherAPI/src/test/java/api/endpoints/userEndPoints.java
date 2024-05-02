package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.Assert;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import api.utilities.ExcelUtilities;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

public class userEndPoints {
	private static List<String> coordinates = new ArrayList();
	public static org.apache.logging.log4j.Logger logger  = LogManager.getLogger();

	public static Response currentWeather(String cityName) {
		Response response;
		cityToGeoLocationConversion(cityName);
		try {
		 response = given()
				  			.pathParam("lat", coordinates.get(0))
				  			.pathParam("lon", coordinates.get(1))
				  			.pathParam("API key", ExcelUtilities.getAPI_Key())
				  	.when()
				  			.get(routes.currentWeather_specificCity);
		 	}
		//System.out.println(response.then().log().all());}
		catch(Exception e) {
			response = given()
							 .pathParam("city", cityName)
							 .pathParam("API key", ExcelUtilities.getAPI_Key())
					   .when()
					   		 .get(routes.cityGeoLocationFinder);
							}
		return response;
	}

	public static List<String> cityToGeoLocationConversion(String cityName) {
		Response response = given()
									.pathParam("city", cityName)
									.pathParam("API key", ExcelUtilities.getAPI_Key())
							.when()
									.get(routes.cityGeoLocationFinder);

		try {
			String lat = response.jsonPath().getList("lat").get(0).toString();
			String lon = response.jsonPath().getList("lon").get(0).toString();
			coordinates.add(lat);
			coordinates.add(lon);
		} catch (Exception e) {
			System.out.println("Invalid City Name: "+cityName);
			logger.info("Invalid City Name: "+cityName);
		}

		return coordinates;
	}
	
	public static Response fiveDayForecast(String cityName) {
		Response response;
		cityToGeoLocationConversion(cityName);
		try {
		 response = given()
				 		   .pathParam("lat", coordinates.get(0))
				 		   .pathParam("lon", coordinates.get(1))
				 		   .pathParam("API key", ExcelUtilities.getAPI_Key())
				 	.when()
				 		   .get(routes.fiveDayForecast_specificCity);
		     }
		//System.out.println(response.then().log().all());}
		catch(Exception e) {
			
			response = given()
							 .pathParam("city", cityName)
							 .pathParam("API key", ExcelUtilities.getAPI_Key())
					   .when()
					   		 .get(routes.fiveDayForecast_specificCity);

		}
		return response;
	}
	
	public static Response invalidAPIKey() {
		Response response = given()
	            .pathParam("API key", "123asd")
	            .pathParam("lat", "51.5074")
	            .pathParam("lon","-0.1278")
	        .when()
	            .get(routes.currentWeather_specificCity);
		logger.info("Invalid API Key entered: ");
		return response;
		
	}
		
	

}
