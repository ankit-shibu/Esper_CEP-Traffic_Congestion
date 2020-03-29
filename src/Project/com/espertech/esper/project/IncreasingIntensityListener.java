package com.espertech.esper.project;

import com.espertech.esper.project.CriticalIntensityEvent;
import java.util.*;

import com.espertech.esper.common.client.EventBean;
import com.espertech.esper.runtime.client.*;

public class IncreasingIntensityListener implements UpdateListener {
	EPRuntime epruntime;
	
	public IncreasingIntensityListener(EPRuntime runtime)
	{
		epruntime = runtime;
	}
    public void update(EventBean[] newEvents, EventBean[] oldEvents,EPStatement statment,EPRuntime runtime)
    {
    	EventBean event = newEvents[0];
    	epruntime.getEventService().sendEventBean(new CriticalIntensityEvent((Date)event.get("timeOfReading")),"CriticalIntensityEvent");
    }
 
}