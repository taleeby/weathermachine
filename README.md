# Weather Machine

# Overview 

This application generates fake weather conditions for various cities and times 

# Dependencies 

You will need the following to run this application 
- Apache Maven 3 
- JDK 1.8 or higher 


# Running it

You can build, test and run the application with; 

`export MAVEN_OPTS=-Xmx500M; mvn clean install`

Maven will build and execute the main program with output like the following; 

```
 _______  __   __  _______    _     _  _______  _______  _______  __   __  _______  ______
 |       ||  | |  ||       |  | | _ | ||       ||   _   ||       ||  | |  ||       ||    _ |
 |_     _||  |_|  ||    ___|  | || || ||    ___||  |_|  ||_     _||  |_|  ||    ___||   | ||
   |   |  |       ||   |___   |       ||   |___ |       |  |   |  |       ||   |___ |   |_||_
   |   |  |       ||    ___|  |       ||    ___||       |  |   |  |       ||    ___||    __  |
   |   |  |   _   ||   |___   |   _   ||   |___ |   _   |  |   |  |   _   ||   |___ |   |  | |
   |___|  |__| |__||_______|  |__| |__||_______||__| |__|  |___|  |__| |__||_______||___|  |_|

 ===================================== T O D A Y  I S  ======================================

Sydney|-33.87,151.21,35|2019-03-14T19:46:18.607+10:00|Rain|+15.3|464.5|8
Melbourne|-37.83,144.98,0|2019-03-14T20:46:18.607+11:00|Rain|+8.0|824.2|3
Adelaide|-34.92,138.62,69|2019-03-14T20:16:18.607+10:30|Rain|+14.9|238.4|5
London|51.51,-0.13,0|2019-03-14T09:46:18.607Z|Rain|+3.2|12.4|3

 ================================ H A V E  A  N I C E  D A Y  ===============================
```

# Design Considerations

The application has been designed to be modular, testable and plugable 
facilitating quick initial delivery of the end to end flow while allowing
for specific enhancements at a later stage.

To enhance the application override one or all of the following and bind your implementation into the 
associated manager using the Spring autowiring annotation `@Qualifer`. 

- `LocationResolver` implementations find the location of a given city to it's latitude and longitude 
- `ElevationResolver` implementations find the elevation of a location by latitude and longitude 
- `TimezoneResolver` implementations find the timezone for a city or location 
- `ConditionGenerator` implementations generate weather conditions for a time and city

For now very simple implementations of the above have been added.


## City Manager UML Sequence 

<img src="docs/uml-sequence-city-manager.png" alt="" width="800"/>


## Condition Manager UML Sequence 

<img src="docs/uml-sequence-condition-manager.png" alt="" width="800"/>

