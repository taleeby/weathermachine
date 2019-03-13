package au.com.alex.weather.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import au.com.alex.weather.AppProperties;
import au.com.alex.weather.model.City;
import au.com.alex.weather.resolver.ResolverException;
import au.com.alex.weather.resolver.elevation.ElevationResolver;
import au.com.alex.weather.resolver.location.LocationResolver;
import au.com.alex.weather.resolver.timezone.TimezoneResolver;
import au.com.alex.weather.store.Store;



/**
 * Choose the City to GPS resolver with;
 * <ul>
 *   <li>@Qualifier("simpleCityResolver") </li>
 *   <li>@Qualifier("complexCityGPSResolver") </li>
 * </ul>
 *   
 */
@Component
public class CityManager {

    @Autowired
    private AppProperties appProperties;

    @Autowired 
	@Qualifier("simpleStore")  
	private Store<City> store;
    
	@Autowired 
	@Qualifier("simpleLocationResolver")  
	private LocationResolver locationResolver;
	
	@Autowired 
	@Qualifier("nasaVisibleEarthElevationResolver")  
	private ElevationResolver elevationResolver;
	
	@Autowired 
	@Qualifier("simpleTimezoneResolver")  
	private TimezoneResolver timezoneResolver;

	/**
	 * Return a city from specified state and country 
	 */
	public City getCity(String name, String state, String country) throws CityNotFoundException {

		CityKey key = new CityKey(name, state, country);
		City city = store.get(key.toString());
		
		if (city==null) {
			CityBuilder builder = new CityBuilder(name)
					.withState(state)
					.withCountry(country)
					.withLocationResolver(locationResolver)
					.withElevationResolver(elevationResolver)
					.withTimezoneResolver(timezoneResolver);
					
			try {
				city = builder.build();
				store.save(key.toString(), city);
			} catch(ResolverException ex) {
				throw new CityNotFoundException("There was a problem creating this city", ex);
			}
		}
		
		return city;
	}

}
