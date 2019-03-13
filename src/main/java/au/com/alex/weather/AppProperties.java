package au.com.alex.weather;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class AppProperties {
	
    public String name;
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


}

