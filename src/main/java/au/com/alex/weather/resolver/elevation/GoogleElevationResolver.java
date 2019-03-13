package au.com.alex.weather.resolver.elevation;

import org.springframework.stereotype.Component;

import au.com.alex.weather.model.City;
import au.com.alex.weather.resolver.ResolverException;

/**
 * TODO: This class is a placeholder to enhance the functionality of location 
 * resolver to find any City using a Google webservice
 * 
 */
@Component
public class GoogleElevationResolver extends ElevationResolver {

	public void resolve(City city) throws ResolverException {
		throw new ResolverException("Not Implemented");
	}
	
}
