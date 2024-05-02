package api.test;

import java.io.IOException;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.userEndPoints;
import api.utilities.ExcelUtilities;
import io.restassured.response.Response;

public class weatherTests {
	
	
		
		//TestData --> fiveDayForecast, currentWeather
		public org.apache.logging.log4j.Logger logger;
		List<String> al;
		 
		@BeforeClass
		void setup() {
			logger  = LogManager.getLogger(this.getClass());
		}
		
		
		@Test
		public void currentWeatherAPI() {
			
			al=ExcelUtilities.getList("currentWeather");
			long responsetime=0;
			long avgtime=0;
			for (String cityName : al) {
				logger.info("*running current weather for "+cityName+"*");
				Response response = userEndPoints.currentWeather(cityName);
				Assert.assertEquals(response.statusCode(), 200);
				responsetime += response.getTime();
				avgtime = responsetime/al.size();	
			}
			Assert.assertTrue(avgtime < 3200L, "Average response time is"+avgtime);
			logger.info("Average response time  " + avgtime+" ms");
    		
		}
		
		
	
@Test()

public void fiveDayForecast() {
	al=ExcelUtilities.getList("fiveDayForecast");
	long responsetime=0;
	long avgtime=0;
	for (String cityName : al) {
		logger.info("*running fiveDayForecast for "+cityName+"*");
		Response response = userEndPoints.currentWeather(cityName);
		Assert.assertEquals(response.statusCode(), 200);
		responsetime += response.getTime();
		avgtime = responsetime/al.size();	
	}
	Assert.assertTrue(avgtime < 3200L, "Average response time is"+avgtime);
	logger.info("Average response time  " + avgtime+ " ms");
	
}

@Test
public void invalidAPIkeyResponse() {
	Response response = userEndPoints.invalidAPIKey();
	logger.info("*running invalidAPIKeyResponse*");
	Assert.assertEquals(response.statusCode(), 401);
	
}
}


