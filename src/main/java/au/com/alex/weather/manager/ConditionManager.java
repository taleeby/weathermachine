package au.com.alex.weather.manager;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import au.com.alex.weather.machine.ConditionGenerator;
import au.com.alex.weather.model.City;
import au.com.alex.weather.model.Condition;



/**
 * Create conditions   
 */
@Component
public class ConditionManager {

	@Autowired 
	@Qualifier("simpleConditionGenerator")  
	private ConditionGenerator conditionGenerator;
	

	/**
	 * Return a city from specified state and country 
	 */
	public Condition createCondition(City city, Date date) {
		return conditionGenerator.createCondition(city, date);
	}

}
