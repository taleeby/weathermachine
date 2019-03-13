package au.com.alex.weather.manager;

public class CityNotFoundException extends Exception {

	private static final long serialVersionUID = 4782311063549073514L;

	public CityNotFoundException(String message, Exception ex) {
		super (message, ex);
	}
}
