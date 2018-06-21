package com.deneme.caulis.caulis.Calendar.Event;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.deneme.caulis.caulis.Calendar.CalendarActivity2;
import com.deneme.caulis.caulis.R;
import com.deneme.caulis.caulis.classes.CaulisEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class EventTab extends Fragment {

    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private CaulisEvent eventsContainer = new CaulisEvent(Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault()));

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.event_tab,container,false);

        final List<String> mutableBookings = new ArrayList<>();
        final ListView bookingsListView = (ListView) v.findViewById(R.id.bookings_listview);
        final ArrayAdapter adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mutableBookings);
        final CalendarActivity2 activity2 = (CalendarActivity2) getActivity();

        bookingsListView.setAdapter(adapter);

        loadEvents();
        loadEventsForYear(2017);
        eventsContainer.addEvent(activity2.getNewSingleEvent());

        //List<Event> bookingsFromMap = activity2.getNewEvent();
        List<Event> bookingsFromMap = eventsContainer.getAllEvents();


        if(!bookingsFromMap.isEmpty()) {
            //mutableBookings.clear();
            for (Event booking : bookingsFromMap) {
                if(booking.getData() != null){
                    mutableBookings.add((String) booking.getData() + " - " + booking.getName() + " (" + booking.getLocation() + ") " + " : " + booking.getDescription());
                }

            }
            adapter.notifyDataSetChanged();
        }

        return v;
    }

    private void loadEvents() {
        addEvents(-1, -1);
        addEvents(Calendar.DECEMBER, -1);
        addEvents(Calendar.AUGUST, -1);
    }

    private void loadEventsForYear(int year) {
        addEvents(Calendar.DECEMBER, year);
        addEvents(Calendar.AUGUST, year);
    }

    private void addEvents(int month, int year) {
        currentCalender.setTime(new Date());
        currentCalender.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = currentCalender.getTime();
        for (int i = 0; i < 6; i++) {
            currentCalender.setTime(firstDayOfMonth);
            if (month > -1) {
                currentCalender.set(Calendar.MONTH, month);
            }
            if (year > -1) {
                currentCalender.set(Calendar.ERA, GregorianCalendar.AD);
                currentCalender.set(Calendar.YEAR, year);
            }
            currentCalender.add(Calendar.DATE, i);
            setToMidnight(currentCalender);
            long timeInMillis = currentCalender.getTimeInMillis();

            List<Event> events = getEvents(timeInMillis, i);

            eventsContainer.addEvents(events);
        }
    }

    private List<Event> getEvents(long timeInMillis, int day) {
        if (day < 2) {
            return Arrays.asList(new Event("Tennis", new Date(timeInMillis), new Date(timeInMillis), "Viertel", "match with Thommy", timeInMillis, Color.argb(255, 169, 68, 65)));
        } else if ( day > 2 && day <= 4) {
            return Arrays.asList(
                    new Event("Birthday (me)", new Date(timeInMillis), new Date(timeInMillis), "Home", "This is my birthday", timeInMillis, Color.argb(255, 169, 68, 65)),
                    new Event("Exam", new Date(timeInMillis), new Date(timeInMillis), "HochScule", "German exam", timeInMillis, Color.argb(255, 100, 68, 65)));
        } else {
            return Arrays.asList(
                    new Event("Trip to Hamburg", new Date(timeInMillis), new Date(timeInMillis), "Hamburg", "I will go see friends in Hamburg", timeInMillis,Color.argb(255, 169, 68, 65)),
                    new Event("Buy a gift for my parents", new Date(timeInMillis), new Date(timeInMillis), "Domsheide", "Don't forget to buy gift for their birthday", timeInMillis,Color.argb(255, 100, 68, 65)),
                    new Event("Birthday of my brother", new Date(timeInMillis), new Date(timeInMillis), "Home", "This is the birthday of my brother", timeInMillis,Color.argb(255, 70, 68, 65)));
        }
    }

    private void setToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

}
