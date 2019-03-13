package au.com.alex.weather.machine;

import java.util.Date;

import au.com.alex.weather.model.City;
import au.com.alex.weather.model.Condition;

public interface ConditionGenerator {

	/**
	 * Create and return a weather condition for the given city at a time
	 * @param city
	 */
	public Condition createCondition(City city, Date date);
	
}
