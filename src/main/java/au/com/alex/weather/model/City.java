package au.com.alex.weather.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class City extends Entity {

	private String name; 
	private String state; 
	private String country; 
	private Double latitude; 
	private Double longitude;
	private int elevation;
	private String timeZoneId;
	private Map<Long, Condition> conditions = new ConcurrentHashMap<Long, Condition>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * TODO: Move to separate related class 
	 */
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	/**
	 * TODO: Move to separate related class 
	 */
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public int getElevation() {
		return elevation;
	}

	public void setElevation(int elevation) {
		this.elevation = elevation;
	}

	public String getTimeZoneId() {
		return timeZoneId;
	}

	public void setTimeZoneId(String timeZoneId) {
		this.timeZoneId = timeZoneId;
	}

	public Map<Long, Condition> getConditions() {
		return conditions;
	}

	public void setConditions(Map<Long, Condition> conditions) {
		this.conditions = conditions;
	}
	
	public String toString() {
		return getName();
	}
	
}
