package com.espertech.esper.project;
import java.util.*;

public class CriticalVelocityEvent {
  
    private Date timeOfReading;

    public CriticalVelocityEvent(Date timeOfReading) {
        this.timeOfReading = timeOfReading;
    }

    public Date getTimeOfReading() {
        return timeOfReading;
    }

    @Override
    public String toString() {
        return "CriricalVelocityEvent";
    }

}

