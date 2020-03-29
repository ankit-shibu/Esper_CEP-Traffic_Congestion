package com.espertech.esper.project;
import java.util.*;

public class VelocityEvent {

    private int velocity;

  
    private Date timeOfReading;


    public VelocityEvent(int velocity,Date timeOfReading) {
        this.velocity = velocity;
        this.timeOfReading = timeOfReading;
    }

    public int getVelocity() {
        return velocity;
    }

    public Date getTimeOfReading() {
        return timeOfReading;
    }

    @Override
    public String toString() {
        return "VelocityEvent [" + velocity + "m/s]";
    }

}

