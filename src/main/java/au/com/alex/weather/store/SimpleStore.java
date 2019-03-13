package au.com.alex.weather.store;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import au.com.alex.weather.model.Entity;

/**
 * This is the simplest implementation for storing an entity 
 * We will use it to store City for the moment 
 *
 * @param <T>
 */
@Component
public class SimpleStore<T extends Entity> extends Store<T> {
	
	protected Map<String, T> store = new ConcurrentHashMap<>();

	@Override
	public void save(String key, T entity) {
		super.save(key, entity);
		store.put(key,  entity);
	}
	
	@Override
	public T get(String key) {
		return store.get(key);
	}
	
}
