package au.com.alex.weather;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import au.com.alex.weather.manager.CityManager;
import au.com.alex.weather.manager.CityNotFoundException;
import au.com.alex.weather.manager.ConditionManager;
import au.com.alex.weather.model.Condition;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class Main implements CommandLineRunner {
	
	@Autowired
	AppProperties appProperties;
	
	@Autowired
	CityManager cityManager;
	
	@Autowired
	ConditionManager conditionManager;
	
    private static Logger LOG = LoggerFactory
      .getLogger(Main.class);
 
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
  
    @Override
    public void run(String... args) {
        LOG.info("EXECUTING : command line runner");
        try {
        
        	List<Condition> conditions = new ArrayList<>();
        	conditions.add(conditionManager.createCondition(cityManager.getCity("Sydney", "NSW", "Australia"), new Date()));
        	conditions.add(conditionManager.createCondition(cityManager.getCity("Melbourne", "", "Australia"), new Date()));
        	conditions.add(conditionManager.createCondition(cityManager.getCity("Adelaide", "", "Australia"), new Date()));
        	conditions.add(conditionManager.createCondition(cityManager.getCity("London", "", "England"), new Date()));
        	
        	System.out.println();
        	System.out.println(" _______  __   __  _______    _     _  _______  _______  _______  __   __  _______  ______   ");
        	System.out.println(" |       ||  | |  ||       |  | | _ | ||       ||   _   ||       ||  | |  ||       ||    _ |  ");
        	System.out.println(" |_     _||  |_|  ||    ___|  | || || ||    ___||  |_|  ||_     _||  |_|  ||    ___||   | ||  ");
        	System.out.println("   |   |  |       ||   |___   |       ||   |___ |       |  |   |  |       ||   |___ |   |_||_ ");
        	System.out.println("   |   |  |       ||    ___|  |       ||    ___||       |  |   |  |       ||    ___||    __  |");
        	System.out.println("   |   |  |   _   ||   |___   |   _   ||   |___ |   _   |  |   |  |   _   ||   |___ |   |  | |");
        	System.out.println("   |___|  |__| |__||_______|  |__| |__||_______||__| |__|  |___|  |__| |__||_______||___|  |_|");
        	System.out.println();
        	System.out.println(" ===================================== T O D A Y  I S  ======================================");
        	System.out.println();
        	for (Condition condition : conditions) {
        		System.out.println(condition);
        	}
        	System.out.println();
        	System.out.println(" ================================ H A V E  A  N I C E  D A Y  ===============================");
        	System.out.println();
        	
        } catch (CityNotFoundException ex) {
        	ex.printStackTrace();
        	LOG.error(ex.getCause().getMessage());
        }
        
    }
}