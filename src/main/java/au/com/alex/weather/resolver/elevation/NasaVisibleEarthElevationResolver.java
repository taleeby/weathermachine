package au.com.alex.weather.resolver.elevation;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

import au.com.alex.weather.model.City;
import au.com.alex.weather.resolver.ResolverException;

/**
 * Resolve elevation from NASA Topography image
 * 
 * @see https://visibleearth.nasa.gov/view.php?id=73934
 * @see https://www.gebco.net/data_and_products/gridded_bathymetry_data/documents/gebco_08.pdf
 */
@Component
public class NasaVisibleEarthElevationResolver extends ElevationResolver {

	public static final int LONDON_ELEVATION = 11;
	public static final double LONDON_LATITUDE = 51.507351;
	public static final double LONDON_LONGITUDE = -0.127758;
	
	public static final int EVEREST_ELEVATION = 8848;
	public static final double EVEREST_LATITUDE = 27.986065;
	public static final double EVEREST_LONGITUDE = 86.922623;
	
	private static String IMAGE_FILE = "gebco_08_rev_elev_21600x10800.png";
	private static int FULL_CIRCLE = 360;
	private static int HALF_CIRCLE = FULL_CIRCLE/2;
			
	private int pixelHeight;
	private int pixelWidth;
	private double pixelToElevationScalingFactor;
	private BufferedImage img;
	
	protected void init() throws ResolverException {
		try {
			ClassLoader classLoader = ClassLoader.getSystemClassLoader();
			
			File file=null;
			
       		URL url = classLoader.getResource(IMAGE_FILE);
            if (url!=null) {
			    file = new File(url.getFile());
            }
		
			if (file==null) {
				url = getClass().getResource("/"+IMAGE_FILE);
                if (url!=null) {
    				file = new File(url.getFile());
                }
			}
			
			if (file==null) {
                throw new ResolverException(String.format("Unable to find %s", IMAGE_FILE));
			}
			
			img = ImageIO.read(file);
			setPixelHeight(img.getHeight());
			setPixelWidth(img.getWidth());
			
			/*
			 * Here we need to determine what the map is actually 
			 * showing. Let's choose a known flat location and 
			 * calculate a scalingFactor that will turn rgb into height 
			 */
    		int x = getImageXCoordFromLongitude(EVEREST_LONGITUDE);
	    	int y = getImageYCoordFromLatitude(EVEREST_LATITUDE);
	    	WritableRaster r = img.getRaster();
	    	double sample = r.getSample(x, y, 0);
			pixelToElevationScalingFactor = sample / EVEREST_ELEVATION;
			
		} catch (IOException e) {
			throw new ResolverException("Unable to load image", e);
		}	
	}

	public double getPixelToElevationScalingFactor() {
		return pixelToElevationScalingFactor;
	}

	public void setPixelToElevationScalingFactor(double pixelToElevationScalingFactor) {
		this.pixelToElevationScalingFactor = pixelToElevationScalingFactor;
	}

	public void resolve(City city) throws ResolverException {

		if (img==null) {
	        init();		
		}
		
		if (city.getLatitude()==null) {
			throw new ResolverException("latitude must be set");
		}
		
		if (city.getLongitude()==null) {
			throw new ResolverException("longitude must be set");
		}
		
		int x = getImageXCoordFromLongitude(city.getLongitude());
		int y = getImageYCoordFromLatitude(city.getLatitude());
		// Image is of type img.getType() == BufferedImage.TYPE_BYTE_GRAY
		// TYPE_BYTE_GRAY, range = [0,255], black = 0 and white = 255.
		WritableRaster r = img.getRaster();
		double sample = r.getSample(x, y, 0);
		int elevation = (int)Math.round(sample/getPixelToElevationScalingFactor());
		
		//System.out.println(String.format("%s rgb(%s,%s) = %s", city.getName(), x, y, elevation));
		
		city.setElevation(elevation);
	}

	protected int getPixelWidth() {
		return pixelWidth;
	}
	
	protected void setPixelWidth(int pixelWidth) {
		this.pixelWidth = pixelWidth;
	}
	
	protected int getPixelHeight() {
		return pixelHeight;
	}
	
	protected void setPixelHeight(int pixelHeight) {
		this.pixelHeight = pixelHeight;
	}
	
	/**
	 * Need pixel coord with 0,0 starting from top left 
	 * 
	 * @param latitude +90 to -90 
	 * @return y image coord 
	 */
	protected int getImageYCoordFromLatitude(Double latitude) {
	
		double lat = latitude.doubleValue();
		lat = lat * -1;                    // Invert GPS coord system to align with image coord system 
		lat = lat + 90;                    // Align zero gps coord with top left 
		
		double ratio = lat / 180;

		double y = ratio * getPixelHeight();
		long result = Math.round(y);
		
		return (int)result;
	}
	
	
	/**
	 * Need pixel coord with 0,0 starting from top left 
	 * 
	 * @param longitude +180 to -180 where 0 (London) is in the middle of the map  
	 * @return x image coord 
	 * @see https://en.wikipedia.org/wiki/Longitude
	 */
	protected int getImageXCoordFromLongitude(Double longitude) {
	
		double lng = longitude.doubleValue();
		lng = lng + HALF_CIRCLE;           // Align zero gps coord with top left 
	
		double ratio = lng / FULL_CIRCLE;
		double x = ratio * getPixelWidth();
		long result = Math.round(x);
		
		return (int)result;
	}
	
	
}
