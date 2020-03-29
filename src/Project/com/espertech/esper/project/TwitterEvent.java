package com.espertech.esper.project;
import java.util.*;

public class TwitterEvent {

    private int count;

  
    private Date timeOfReading;


    public TwitterEvent(int count,Date timeOfReading) {
        this.count = count;
        this.timeOfReading = timeOfReading;
    }

    public int getCount() {
        return count;
    }

    public Date getTimeOfReading() {
        return timeOfReading;
    }

    @Override
    public String toString() {
        return "TwitterEvent [" + count + "]";
    }

}

