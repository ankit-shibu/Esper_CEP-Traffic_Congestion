package com.espertech.esper.project;

import com.espertech.esper.common.client.EventBean;
import com.espertech.esper.runtime.client.*;
import com.espertech.esper.project.FeaturesEvent;

public class ReadDataListener implements UpdateListener {
	EPRuntime epruntime;
	private int weather,timeofday;
	public ReadDataListener(EPRuntime runtime)
	{
		epruntime = runtime;
	}
    public void update(EventBean[] newEvents, EventBean[] oldEvents,EPStatement statment,EPRuntime runtime)
    {
    	EventBean event = newEvents[0];
    	String weather_status = (String)event.get("weather_status");
    	if(weather_status.equals("partly-cloudy-day")||weather_status.equals("partly-cloudy-night")||weather_status.equals("clear-night"))
    		weather = 0;
    	else if(weather_status.equals("fog")||weather_status.equals("cloudy"))
    		weather = 1;
    	else
    		weather = 2;
    	String time = (String)event.get("time");
    	int hour=1;
    	try {
        	hour = (int)Integer.parseInt(time.substring(11, 13));
    	}catch(Exception e)
    	{
    		System.out.println("Change time format");
    	}
    	if(hour>=8&&hour<=12)
    		timeofday=1;
    	else if(hour>12&&hour<=16)
    		timeofday=1;
    	else if(hour>16&&hour<=20)
    		timeofday=1;
    	else
    		timeofday=1;
    	System.out.println("Combined Event generated for "+(String)event.get("time")+" "+(int)event.get("velocity")+" "+(int)event.get("intensity")+" "+(int)event.get("tweet_count")+" "+weather+" "+timeofday);
    	//runtime.getEventService().sendEventBean(new FeaturesEvent((int)event.get("velocity"),(int)event.get("intensity"),(int)event.get("tweet_count"),(String)event.get("weather_status"),(String)event.get("time"),weather,timeofday),"FeaturesEvent");
    }
 
}