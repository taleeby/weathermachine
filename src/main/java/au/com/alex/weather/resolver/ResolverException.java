package au.com.alex.weather.resolver;

public class ResolverException extends Exception {

	private static final long serialVersionUID = -1862725440113584232L;

	public ResolverException(String message) {
		super(message);
	}
	
	public ResolverException(String message, Exception e) {
		super(message, e);
	}
}
