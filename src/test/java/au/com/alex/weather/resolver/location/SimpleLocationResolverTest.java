package au.com.alex.weather.resolver.location;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import au.com.alex.weather.model.City;
import au.com.alex.weather.resolver.ResolverException;
import au.com.alex.weather.resolver.location.SimpleLocationResolver;

public class SimpleLocationResolverTest {
	
	private SimpleLocationResolver resolver = new SimpleLocationResolver();

	@Test
	public void testFound() {
		City city = getCity("Sydney");
		
		assertNull(city.getLatitude());
		assertNull(city.getLongitude());
	
		try {
			resolver.resolve(city);
		} catch (ResolverException e) {
			fail(e.getMessage());
		}
		
		assertNotNull(city.getLatitude());
		assertNotNull(city.getLongitude());
		
		assertEquals(new Double(-33.8688), city.getLatitude());
		assertEquals(new Double(151.2093), city.getLongitude());
	}


	@Test
	public void testNotFound() {
		City city = getCity("TomTomImaginary");
		
		assertNull(city.getLatitude());
		assertNull(city.getLongitude());
	
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
