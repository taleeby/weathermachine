package au.com.alex.weather.model;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class Condition extends Entity {

	public static enum Type {Snow, Rain, Sunny}; 

	private Date time; 
	private Type type;
    private Double temperature; 
    private Double humidity; 
    private Double pressure;
    private City city;
    
	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public Double getTemperature() {
		return temperature;
	}
	
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	
	public Double getHumidity() {
		return humidity;
	}
	
	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}
	
	public Double getPressure() {
		return pressure;
	}
	
	public void setPressure(Double pressure) {
		this.pressure = pressure;
	} 

	
	public City getCity() {
		return city;
	}
	
	public void setCity(City city) {
		this.city = city;
	} 

	
	public String toString() {
		
		List<String> positions = Arrays.asList(String.format("%.2f", getCity().getLatitude())
				                              ,String.format("%.2f", getCity().getLongitude())
				                              ,String.format("%s", getCity().getElevation()));
				                              
		String position = positions.stream().collect(Collectors.joining(","));

		SimpleDateFormat sdf;
		sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		sdf.setTimeZone(TimeZone.getTimeZone(city.getTimeZoneId()));
		String date = sdf.format(getTime());
		
		List<String> items = Arrays.asList( getCity().getName()
				                           ,position
				                           ,date
				                           ,getType().name()
				                           ,String.format("%+.1f", getTemperature())
				                           ,String.format("%.1f", getPressure())
				                           ,String.format("%.0f", getHumidity())
										  );
		String citiesSeparated = items.stream().collect(Collectors.joining("|"));

		return citiesSeparated;
	}	
	
}
