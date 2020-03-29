package com.espertech.esper.project;
import java.util.*;

public class WeatherEvent {

    private String weather;

  
    private Date timeOfReading;


    public WeatherEvent(String weather,Date timeOfReading) {
        this.weather = weather;
        this.timeOfReading = timeOfReading;
    }

    public String getWeather() {
        return weather;
    }

    public Date getTimeOfReading() {
        return timeOfReading;
    }

    @Override
    public String toString() {
        return "WeatherEvent [" + weather + "]";
    }

}

