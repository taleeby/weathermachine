package au.com.alex.weather.resolver;

import au.com.alex.weather.model.City;

public interface Resolver {

	/**
	 * Resolve some information for a city 
	 * 
	 * @param city information we currently have for the city 
	 * @return the city enhanced with information from this resolver
	 * @throws ResolverException
	 */
	public void resolve(City city) throws ResolverException;
	
}
