
/***************************************************************************************************
 * A container for events
 **************************************************************************************************/

package com.deneme.caulis.caulis.classes;


import java.sql.Time;
import java.util.Date;

public class CaulisEvent {
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
    /*private arraylist<> users;*/

    public CaulisEvent(String name, Date startDate, Date endDate, Time startTime, Time endTime, String location, String description, int numberOfPeopleAllowed){
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

}
