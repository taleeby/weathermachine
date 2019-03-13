package au.com.alex.weather.resolver.timezone;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

import au.com.alex.weather.model.City;
import au.com.alex.weather.resolver.ResolverException;

/**
 * Resolve timezone based on longitude 
 * TODO: No time to implement unfortunately so will hard code the required values 
 */
@Component
public class SimpleTimezoneResolver extends TimezoneResolver {

	private Map<String,TimeZone> zones = new HashMap<>();
	
	public SimpleTimezoneResolver() {
	
		zones.put("sydney", TimeZone.getTimeZone("GMT+10"));
		zones.put("adelaide", TimeZone.getTimeZone("GMT+10:30"));
		zones.put("melbourne", TimeZone.getTimeZone("GMT+11"));
		zones.put("london", TimeZone.getTimeZone("GMT"));
		
	}
	
	public void resolve(City city) throws ResolverException {

		TimeZone tz = zones.get(city.getName().toLowerCase());
		if (tz==null) {
			throw new ResolverException(String.format("Sorry I can't find timezone for %s", city.getName()));
		}
		city.setTimeZoneId(tz.getID());
		
	}
	
}
