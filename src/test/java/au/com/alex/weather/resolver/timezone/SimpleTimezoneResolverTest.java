package au.com.alex.weather.resolver.timezone;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import au.com.alex.weather.model.City;
import au.com.alex.weather.resolver.ResolverException;

public class SimpleTimezoneResolverTest {
	
	private SimpleTimezoneResolver resolver = new SimpleTimezoneResolver();

	@Test
	public void testFound() {
		City city = getCity("Sydney");
		
		assertNull(city.getTimeZoneId());
	
		try {
			resolver.resolve(city);
		} catch (ResolverException e) {
			fail(e.getMessage());
		}
		
		assertNotNull(city.getTimeZoneId());
	}


	@Test
	public void testNotFound() {
		City city = getCity("TomTomImaginary");
		
		assertNull(city.getTimeZoneId());
	
		try {
			resolver.resolve(city);
			fail("Expected this city to not be found");
		} catch (ResolverException e) {
		}
	}

	
	private City getCity(String name) {
		City city = new City();
		city.setName(name);
		return city;
	}
	
}
