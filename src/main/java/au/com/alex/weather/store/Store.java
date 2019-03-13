package au.com.alex.weather.store;

import java.util.Date;

import org.springframework.stereotype.Component;

import au.com.alex.weather.model.Entity;

/**
 * We will store already found weather conditions simply at first but 
 * leave scope for complex storage later on 
 *
 * @param <T>
 */
public abstract class Store<T extends Entity> {
	

	public void save(String key, T entity) {
		entity.setLastUpdated(new Date());
	}
	
	public abstract T get(String key);
	
}
