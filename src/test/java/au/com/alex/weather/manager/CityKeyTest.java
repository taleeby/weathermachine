package au.com.alex.weather.manager;


import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class CityKeyTest {

	@Test
	public void testMixedCase() {
	
		assertEquals(new CityKey("sydney", "nsw", "australia").toString(), new CityKey("sydney", "nsw", "australia").toString());
		assertEquals(new CityKey("Sydney", "nsw", "Australia").toString(), new CityKey("sydney", "nsw", "australia").toString());
		assertEquals(new CityKey("Sydney", "NSW", "Australia").toString(), new CityKey("sydney", "nsw", "australia").toString());
		
	}

}
