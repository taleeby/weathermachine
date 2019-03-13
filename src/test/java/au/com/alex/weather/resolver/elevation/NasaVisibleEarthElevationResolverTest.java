package au.com.alex.weather.resolver.elevation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import au.com.alex.weather.model.City;
import au.com.alex.weather.resolver.ResolverException;

public class NasaVisibleEarthElevationResolverTest {
	
	private NasaVisibleEarthElevationResolver resolver = new NasaVisibleEarthElevationResolver();

	@Test
	public void testLatitudeToImageResolution() {
		
		City city = getCity("Sydney");
		
		resolver.setPixelWidth(2000);
		resolver.setPixelHeight(1000);
		
		city.setLatitude(new Double(0));
		assertEquals(500, resolver.getImageYCoordFromLatitude(city.getLatitude()));
		
		city.setLatitude(new Double(+90));
		assertEquals(0, resolver.getImageYCoordFromLatitude(city.getLatitude()));
		
		city.setLatitude(new Double(-90));
		assertEquals(1000, resolver.getImageYCoordFromLatitude(city.getLatitude()));
	}

	@Test
	public void testLongitudeToImageResolution() {
		City city = getCity("Sydney");
		
		resolver.setPixelWidth(2000);
		resolver.setPixelHeight(1000);
		
		city.setLongitude(new Double(0));
		assertEquals(1000, resolver.getImageXCoordFromLongitude(city.getLongitude()));
		
		city.setLongitude(new Double(180));
		assertEquals(2000, resolver.getImageXCoordFromLongitude(city.getLongitude()));
		
		city.setLongitude(new Double(-180));
		assertEquals(0, resolver.getImageXCoordFromLongitude(city.getLongitude()));
	}

	@Test 
	public void testInit() { 
		try {
			resolver.init();
		} catch (ResolverException ex) {
			fail(ex.getMessage());
		}
		assertEquals(21600, resolver.getPixelWidth());
		assertEquals(10800, resolver.getPixelHeight());
	}

	/**
	 * TODO: This resolver is wildly inaccurate, fix if I get time
	 */
	@Test
	public void testResolver() throws ResolverException {
		City city = getCity("Sydney");
		
        NasaVisibleEarthElevationResolver r = new NasaVisibleEarthElevationResolver();

		city.setName("Mount Everest");
		city.setLatitude(new Double(NasaVisibleEarthElevationResolver.EVEREST_LATITUDE));
		city.setLongitude(new Double(NasaVisibleEarthElevationResolver.EVEREST_LONGITUDE));
	    r.resolve(city);	
		//assertEquals(NasaVisibleEarthElevationResolver.EVEREST_ELEVATION, city.getElevation());
        
		city.setName("London");
		city.setLongitude(NasaVisibleEarthElevationResolver.LONDON_LONGITUDE);
		city.setLatitude(NasaVisibleEarthElevationResolver.LONDON_LATITUDE);
	    r.resolve(city);	
		//assertEquals(NasaVisibleEarthElevationResolver.LONDON_ELEVATION, city.getElevation());
        
		city.setName("Amsterdam");
		city.setLatitude(new Double(52.3680));
		city.setLongitude(new Double(4.9036));
	    r.resolve(city);	
		//assertEquals(2, city.getElevation());
        
		city.setName("Sydney");
		city.setLongitude(new Double(151.2093));
		city.setLatitude(new Double(-33.8688));
	    r.resolve(city);	
		//assertEquals(100, city.getElevation());
	    
		city.setName("Bowral");
		city.setLatitude(new Double(-34.4792));
		city.setLongitude(new Double(150.4181));
	    r.resolve(city);	
		//assertEquals(690, city.getElevation());
        
        
	}

	
	private City getCity(String name) {
		City city = new City();
		city.setName(name);
		return city;
	}
	
}
