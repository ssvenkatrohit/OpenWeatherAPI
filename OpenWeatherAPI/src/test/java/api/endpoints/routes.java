package api.endpoints;

import com.github.javafaker.Color;
import com.github.javafaker.Faker;

public class routes {
	
	public static String baseURI = "http://api.openweathermap.org";
	public static String currentWeather_specificCity = baseURI + "/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}&units=metric";
	public static String fiveDayForecast_specificCity = baseURI + "/data/2.5/forecast?lat={lat}&lon={lon}&appid={API key}";
	public static String cityGeoLocationFinder = baseURI+ "/geo/1.0/direct?q={city}&limit=5&appid={API key}";	
	
}
