package com.espertech.esper.project;
public class Features {

    private int velocity;
    private int intensity;
    private int tweet_count;
    private int weather;
    private int timeofday;
    private String time;
    private String weather_status;


    public Features(int velocity,int intensity,int tweet_count,String weather_status,String time,int weather,int timeofday) {
        this.velocity = velocity;
        this.intensity = intensity;
        this.tweet_count = tweet_count;
        this.weather_status = weather_status;
        this.time = time;
        this.weather=weather;
        this.timeofday = timeofday;
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

