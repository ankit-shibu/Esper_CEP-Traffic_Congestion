package com.espertech.esper.project;
public class CombinedEvent {

    private int velocity;
    private int intensity;
    private int tweet_count;
    private String time;
    private String weather_status;


    public CombinedEvent(int velocity,int intensity,int tweet_count,String weather_status,String time) {
        this.velocity = velocity;
        this.intensity = intensity;
        this.tweet_count = tweet_count;
        this.weather_status = weather_status;
        this.time = time;
        //this.timeOfReading = timeOfReading;
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
    public String getweather_status() {
        return weather_status;
    }    
    @Override
    public String toString() {
        return "Velocity [" + velocity + "m/s],intensity [\" + intensity + \"],tweet_count [\" + tweet_count + \"],weather_status [\" + weather_status + \"]";
    }

}

