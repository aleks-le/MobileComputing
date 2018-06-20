
/***************************************************************************************************
 * Event
 **************************************************************************************************/


package com.deneme.caulis.caulis.Calendar;

import android.graphics.Color;
import android.support.annotation.Nullable;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Event {

    private int color;
    private long timeInMillis;
    private Object data;

    private String name;
    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;
    private String location;
    private String description;
    private int numberOfPeopleAllowed;/*Not necessary*/
    //private int eventID;
    //private int authorID;
    /*private arraylist<> users;*/


    public Event(String name, Date startDate, Date endDate, String location, String description, long timeInMillis){
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.description = description;
        this.timeInMillis = timeInMillis;
        SimpleDateFormat d = new SimpleDateFormat("MM/dd/yyyy");
        Date dateString = new Date(timeInMillis);
        this.data = " " + d.format(dateString);
        this.color = Color.argb(255, 169, 68, 65);
    }

    public Event(String name, Date startDate, Date endDate, String location, String description, long timeInMillis, int color){
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.description = description;
        this.timeInMillis = timeInMillis;
        this.data = new Date(timeInMillis);
        this.color = Color.argb(255, 169, 68, 65);
    }

    public Event(int color, long timeInMillis) {
        this.color = color;
        this.timeInMillis = timeInMillis;
    }

    public Event(int color, long timeInMillis, Object data) {
        this.color = color;
        this.timeInMillis = timeInMillis;
        this.data = data;
    }

    public long getTimeInMillis(){return this.timeInMillis;}
    public void setTimeInMillis(long newTimeInMillis){this.timeInMillis = newTimeInMillis;}
    public String getName(){return this.name;}
    public Date getStartDate(){return this.startDate;}
    public void setStartDate(Date newStartDate){this.startDate = newStartDate;}
    public Date getEndDate(){return this.endDate;}
    public Time getStartTime(){return this.startTime;}
    public Time getEndTime(){return this.endTime;}
    public String getLocation(){return this.location;}
    public String getDescription(){return this.description;}
    public int getNumberOfPeopleAllowed(){return this.numberOfPeopleAllowed;}
    //public int getEventID(){return this.eventID;}
    //public int getAuthorID(){return this.authorID;}
    public int getColor(){return this.color;}


    @Nullable
    public Object getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (color != event.color) return false;
        if (timeInMillis != event.timeInMillis) return false;
        if (data != null ? !data.equals(event.data) : event.data != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = color;
        result = 31 * result + (int) (timeInMillis ^ (timeInMillis >>> 32));
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "color=" + color +
                ", timeInMillis=" + timeInMillis +
                ", data=" + data +
                '}';
    }
}
