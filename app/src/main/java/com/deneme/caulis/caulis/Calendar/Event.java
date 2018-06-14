
/***************************************************************************************************
 * Event
 **************************************************************************************************/


package com.deneme.caulis.caulis.Calendar;

import java.sql.Time;
import java.util.Date;

public class Event {

    private long timeInMillis; //Maybe

    private String name;
    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;
    private String location;
    private String description;
    private int numberOfPeopleAllowed;/*Not necessary*/
    private int eventID;
    private int authorID;
    private int color;
    /*private arraylist<> users;*/

    public Event(String name, Date startDate, Date endDate, Time startTime, Time endTime, String location, String description, int numberOfPeopleAllowed){
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.description = description;
        this.numberOfPeopleAllowed = numberOfPeopleAllowed;
        //generate eventID
        //get authorID
        //create users
    }

    public long getTimeInMillis(){return this.timeInMillis;}
    public String getName(){return this.name;}
    public Date getStartDate(){return this.startDate;}
    public Date getEndDate(){return this.endDate;}
    public Time getStartTime(){return this.startTime;}
    public Time getEndTime(){return this.endTime;}
    public String getLocation(){return this.location;}
    public String getDescription(){return this.description;}
    public int getNumberOfPeopleAllowed(){return this.numberOfPeopleAllowed;}
    public int getEventID(){return this.eventID;}
    public int getAuthorID(){return this.authorID;}
    public int getColor(){return this.color;}

}
