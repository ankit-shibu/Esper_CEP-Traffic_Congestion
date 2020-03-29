package com.espertech.esper.project;
public class FeaturesEvent {

    private int velocity;
    private int intensity;
    private int tweet_count;
    private int weather;
    private int timeofday;
    private int typeofday;
    private String time;
    private String weather_status;


    public FeaturesEvent(int velocity,int intensity,int tweet_count,int weather,String time,int timeofday,int typeofday) {
        this.velocity = velocity;
        this.intensity = intensity;
        this.tweet_count = tweet_count;
        this.weather = weather;
        this.time = time;
        this.timeofday = timeofday;
        this.typeofday = typeofday;
        System.out.println("Features Event generated");
    }
    public int getVelocity() {
        return velocity;
    }
    public String getTime() {
        return time;
    }
    public int getintensity() {
        return intensity;
    }
    public int gettweet_count() {
        return tweet_count;
    }
    
    public int gettimeofday() {
        return timeofday;
    }
    
    public int gettypeofday() {
        return typeofday;
    }
    public String getweather_status() {
        return weather_status;
    }
    
    public int getweather() {
        return weather;
    }

    @Override
    public String toString() {
        return "Velocity [" + velocity + "m/s],intensity [\" + intensity + \"],tweet_count [\" + tweet_count + \"],weather_status [\" + weather_status + \"]";
    }

}

