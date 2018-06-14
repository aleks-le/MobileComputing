
/***************************************************************************************************
 * A container for events
 **************************************************************************************************/

package com.deneme.caulis.caulis.classes;


import com.deneme.caulis.caulis.Calendar.Event;
import com.deneme.caulis.caulis.Calendar.Events;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaulisEvent implements CaulisClasses{

    private Map<String, List<Events>> allEvents = new HashMap<>();
    private Calendar eventsCalendar;

    public CaulisEvent(Calendar eventsCalendar){
        this.eventsCalendar = eventsCalendar;
    }


    public void addEvent(Event event) {
        eventsCalendar.setTimeInMillis(event.getTimeInMillis());
        String key = getKeyForCalendarEvent(eventsCalendar);
        List<Events> eventsForMonth = allEvents.get(key);
        if (eventsForMonth == null) {
            eventsForMonth = new ArrayList<>();
        }
        Events eventsForTargetDay = getEventsDay(event.getTimeInMillis());
        if (eventsForTargetDay == null) {
            List<Event> events = new ArrayList<>();
            events.add(event);
            eventsForMonth.add(new Events(event.getTimeInMillis(), events));
        } else {
            eventsForTargetDay.getEvents().add(event);
        }
        allEvents.put(key, eventsForMonth);
    }

    void addEvents(List<Event> events) {
        int count = events.size();
        for (int i = 0; i < count; i++) {
            addEvent(events.get(i));
        }
    }

    public Events getEventsDay(long eventTimeInMillis){
        eventsCalendar.setTimeInMillis(eventTimeInMillis);
        int dayInMonth = eventsCalendar.get(Calendar.DAY_OF_MONTH);
        String keyForCalendarEvent = getKeyForCalendarEvent(eventsCalendar);
        List<Events> eventsForMonthsAndYear = allEvents.get(keyForCalendarEvent);
        if (eventsForMonthsAndYear != null) {
            for (Events events : eventsForMonthsAndYear) {
                eventsCalendar.setTimeInMillis(events.getTimeInMillis());
                int dayInMonthFromCache = eventsCalendar.get(Calendar.DAY_OF_MONTH);
                if (dayInMonthFromCache == dayInMonth) {
                    return events;
                }
            }
        }
        return null;
    }

    public List<Event> getEventsFor(long epochMillis) {
        Events events = getEventsDay(epochMillis);
        if (events == null) {
            return new ArrayList<>();
        } else {
            return events.getEvents();
        }
    }

    public List<Event> getEventsForMonth(long eventTimeInMillis){
        eventsCalendar.setTimeInMillis(eventTimeInMillis);
        String keyForCalendarEvent = getKeyForCalendarEvent(eventsCalendar);
        List<Events> events = allEvents.get(keyForCalendarEvent);
        List<Event> allEventsForMonth = new ArrayList<>();
        if (events != null) {
            for(Events eve : events){
                if (eve != null) {
                    allEventsForMonth.addAll(eve.getEvents());
                }
            }
        }
        return allEventsForMonth;
    }

    List<Events> getEventsForMonthAndYear(int month, int year){
        return allEvents.get(year + "_" + month);
    }

    //E.g. 4 2016 becomes 2016_4
    private String getKeyForCalendarEvent(Calendar cal) {
        return cal.get(Calendar.YEAR) + "_" + cal.get(Calendar.MONTH);
    }








     /*
    private String name;
    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;
    private String location;
    private String description;
    private int numberOfPeopleAllowed;//Not necessary
    private int eventID;
    private int authorID;
    /*private arraylist<> users;*/

    /*
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
    }*/

}

