package au.com.alex.weather.model;

import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

import au.com.alex.weather.model.Condition.Type;

public class ConditionTest {

	@Test
	public void testOutput() {
		City city = new City();
		city.setCountry("Australia");
		city.setElevation(80);
		city.setName("Sydney");
		city.setState("NSW");
		city.setTimeZoneId(TimeZone.getTimeZone("Sydney").getID());
		city.setLongitude(151.2093);
		city.setLatitude(-33.8688);
	
		Condition condition = new Condition();
		condition.setCity(city);
		condition.setHumidity(12.2);
		condition.setPressure(2.2);
		condition.setTemperature(22.1);
		condition.setType(Type.Rain);
		condition.setTime(new Date());
	
		System.out.println(condition);
		
	}

}
