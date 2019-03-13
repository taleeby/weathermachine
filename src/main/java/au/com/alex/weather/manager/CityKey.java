package au.com.alex.weather.manager;

/**
 * Ensure to keep a central point for generating unique lookup key for the store 
 */
public class CityKey {
	
	public static final String SEPERATOR = ".";

	private String name; 
	private String state; 
	private String country; 
	
	public CityKey(String name, String state, String country) {
		this.name = name; 
		this.state = state; 
		this.country = country; 
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name.toLowerCase());
		builder.append(SEPERATOR);
		builder.append(state.toLowerCase());
		builder.append(SEPERATOR);
		builder.append(country.toLowerCase());
		
		return builder.toString();
	}
}
