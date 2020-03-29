package com.espertech.esper.project;
import java.util.*;

public class IntensityEvent {

    private int intensity;

  
    private Date timeOfReading;


    public IntensityEvent(int intensity,Date timeOfReading) {
        this.intensity = intensity;
        this.timeOfReading = timeOfReading;
    }

    public int getIntensity() {
        return intensity;
    }

    public Date getTimeOfReading() {
        return timeOfReading;
    }

    @Override
    public String toString() {
        return "IntensityEvent [" + intensity + "]";
    }

}

