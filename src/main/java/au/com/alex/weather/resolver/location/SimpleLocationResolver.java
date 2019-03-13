package au.com.alex.weather.resolver.location;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import au.com.alex.weather.model.City;
import au.com.alex.weather.resolver.ResolverException;
import au.com.alex.weather.resolver.elevation.NasaVisibleEarthElevationResolver;

/**
 * This is the simplest possible implementation of location resolver. 
 * It is not concerned with performance, functionality or even code quality
 * only that the interface works
 */
@Component
public class SimpleLocationResolver extends LocationResolver {

	private Map<String,Double> latitude = new HashMap<>();
	private Map<String,Double> longitude = new HashMap<>();
	
	public SimpleLocationResolver() {
		
		longitude.put("sydney", 151.2093);
		latitude.put("sydney", -33.8688);
	
		longitude.put("london", -0.127758);
		latitude.put("london", 51.507351);
		
		longitude.put("adelaide", 138.62);
		latitude.put("adelaide", -34.92);
		
		longitude.put("melbourne", 144.98);
		latitude.put("melbourne", -37.83);
		
	}
	
	public void resolve(City city) throws ResolverException {

		String name = city.getName().toLowerCase();
		if (latitude.containsKey(name)
				&& longitude.containsKey(name)) {
			
			city.setLongitude(longitude.get(name));
			city.setLatitude(latitude.get(name));
			
		} else {
			throw new ResolverException(String.format("Sorry I don't know where %s is", name));
		}
		
	}
	
}
