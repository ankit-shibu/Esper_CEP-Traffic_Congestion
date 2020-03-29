package com.espertech.esper.project;
import java.util.*;

public class CriticalIntensityEvent{
  
    private Date timeOfReading;

    public CriticalIntensityEvent(Date timeOfReading) {
        this.timeOfReading = timeOfReading;
    }

    public Date getTimeOfReading() {
        return timeOfReading;
    }

    @Override
    public String toString() {
        return "CriticalIntensityEvent";
    }

}

