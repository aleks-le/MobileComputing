
/***************************************************************************************************
 * A list of Event that are the same day
 **************************************************************************************************/


package com.deneme.caulis.caulis.Calendar;

import java.util.List;

public class Events {

    private List<Event> events;
    private long timeInMillis;

    public Events(long TimeInMillis, List<Event> events) {
        this.events = events;
        this.timeInMillis = TimeInMillis;
    }

    public long getTimeInMillis(){return this.timeInMillis;}
    public List<Event> getEvents(){return  this.events;}

}
