package com.espertech.esper.project;
import com.espertech.esper.common.client.EventBean;
import com.espertech.esper.runtime.client.*;

public class Threshold1Listener implements UpdateListener {
	EPRuntime epruntime;
	public Threshold1Listener(EPRuntime runtime)
	{
		epruntime = runtime;
	}
    public void update(EventBean[] newEvents, EventBean[] oldEvents,EPStatement statment,EPRuntime runtime)
    {
    	System.out.println(statment);
    	EventBean event = newEvents[0];	
    	System.out.println("!!!!!!!!!!!!!!!!!!!Congestion detected!!!!!!!!!!!!!!!!!!!!!!"+' '+(String)event.get("ce1"));
    }
 
}