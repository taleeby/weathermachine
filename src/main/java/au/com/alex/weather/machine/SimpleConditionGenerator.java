package au.com.alex.weather.machine;

import java.util.Date;

import org.springframework.stereotype.Component;

import au.com.alex.weather.model.City;
import au.com.alex.weather.model.Condition;
import au.com.alex.weather.model.Condition.Type;

/**
 * The simplest implementation of condition generator, really just random 
 * numbers
 *
 */
@Component 
public class SimpleConditionGenerator implements ConditionGenerator {

	/**
	 * Create a weather condition for the given city at a time
	 * @param city
	 */
	public Condition createCondition(City city, Date date) {
		Condition condition = new Condition(); 
		condition.setCity(city);
		condition.setType(Type.Rain);
		condition.setHumidity(10*Math.random());
		condition.setTemperature(20*Math.random());
		condition.setPressure(1000*Math.random());
		condition.setTime(date);
		
		city.getConditions().put(date.getTime(), condition);
		return condition;
	}
	
}
