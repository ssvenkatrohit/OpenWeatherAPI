package api.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;

import api.endpoints.userEndPoints;
import io.restassured.response.Response;

public class helperMethods {
	public static void main(String[] args) throws IOException {
		currentWeatherSpecificCity("london");
	}
	

public static List<String> currentWeatherSpecificCity(String cityName) throws IOException {
	String filePath = "C:\\Users\\USER\\eclipse-workspace\\assignment\\OpenWeatherUI\\testData\\Api.xlsx";
		
		List<String> al = new ArrayList();
		
			//logger.info("*running current weather for "+cityName+"*");
			Response response = userEndPoints.currentWeather(cityName);
			Assert.assertEquals(response.statusCode(), 200);
			
			
		
			String temp = response.jsonPath().get("main.temp").toString();
			String pressure =response.jsonPath().get("main.pressure").toString();
			String humidity=response.jsonPath().get("main.humidity").toString();
			
			al.add( temp);
			al.add( pressure);
			al.add(pressure);
			ExcelUtilities.writeListToExcel(filePath,al);
			System.out.println(Arrays.asList(al));
			return al;
		
			
			
			
			
		
}

}
