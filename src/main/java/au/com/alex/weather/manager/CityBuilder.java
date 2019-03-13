package au.com.alex.weather.manager;

import au.com.alex.weather.model.City;
import au.com.alex.weather.resolver.ResolverException;
import au.com.alex.weather.resolver.elevation.ElevationResolver;
import au.com.alex.weather.resolver.location.LocationResolver;
import au.com.alex.weather.resolver.timezone.TimezoneResolver;


/**
 * <p>
 * Create a city 
 * </p>
 *
 * <p>
 * I'll explicitly not use Spring here to show builder injected with expected behaviour 
 * </p>
 * 
 * <p>
 * Also let's not include this class within the City class to ensure we can use City as 
 * an uncluttered value object
 * </p>
 * 
 */
public class CityBuilder {

	private final String name;
	private String state;
	private String country;
	private LocationResolver locationResolver;
	private ElevationResolver elevationResolver;
	private TimezoneResolver timezoneResolver;
	

	public CityBuilder(String name) {
		this.name = name;
	}
	
	public CityBuilder withState(String state) {
		this.state = state;
		return this;
	}
	
	public CityBuilder withCountry(String country) {
		this.country = country;
		return this;
	}
	
	public CityBuilder withLocationResolver(LocationResolver locationResolver) {
		this.locationResolver = locationResolver;
		return this;
	}
	
	public CityBuilder withElevationResolver(ElevationResolver elevationResolver) {
		this.elevationResolver = elevationResolver;
		return this;
	}
	
	public CityBuilder withTimezoneResolver(TimezoneResolver timezoneResolver) {
		this.timezoneResolver = timezoneResolver;
		return this;
	}
	
	public City build() throws ResolverException {
		City city = new City();
		city.setName(name);
		city.setState(state);
		city.setCountry(country);
		
	    if (locationResolver==null)	{
	    	throw new ResolverException("Please provide a location resolver"); 
	    }
	    locationResolver.resolve(city);
	   
	    if (elevationResolver==null)	{
	    	throw new ResolverException("Please provide an elevation resolver"); 
	    }
	    elevationResolver.resolve(city);
	   
	    if (timezoneResolver==null)	{
	    	throw new ResolverException("Please provide a timezone resolver"); 
	    }
	    timezoneResolver.resolve(city);
	   
	    return city;
	}
}
