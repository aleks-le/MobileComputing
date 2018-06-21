package com.deneme.caulis.caulis.Calendar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.deneme.caulis.caulis.Calendar.Event.Event;
import com.deneme.caulis.caulis.Calendar.Event.EventActivity;
import com.deneme.caulis.caulis.Group.GroupActivity;
import com.deneme.caulis.caulis.Group.GroupPageActivity;
import com.deneme.caulis.caulis.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class CalendarTab extends Fragment {

    private static final String TAG = "MainActivity";
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private CalendarView compactCalendarView;
    private ActionBar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.calendar_tab,container,false);

        final List<String> mutableBookings = new ArrayList<>();
        final ListView bookingsListView = (ListView) v.findViewById(R.id.bookings_listview);
        final Button showPreviousMonthBut = (Button) v.findViewById(R.id.prev_button);
        final Button showNextMonthBut = (Button) v.findViewById(R.id.next_button);
        final Button addEventButton = (Button) v.findViewById(R.id.addEventButton);
        final Button addGroupButton = (Button) v.findViewById(R.id.addGroupButton);
        final CalendarActivity2 activity2 = (CalendarActivity2) getActivity();


        final ArrayAdapter adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mutableBookings);
        bookingsListView.setAdapter(adapter);
        compactCalendarView = (CalendarView) v.findViewById(R.id.calendar_view);

        // below allows you to configure color for the current day in the month
        // compactCalendarView.setCurrentDayBackgroundColor(getResources().getColor(R.color.black));
        // below allows you to configure colors for the current day the user has selected
        // compactCalendarView.setCurrentSelectedDayBackgroundColor(getResources().getColor(R.color.dark_red));
        compactCalendarView.setUseThreeLetterAbbreviation(false);
        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);

        /*if(activity2.getNewSingleEvent() != null) {
            long startDate = activity2.getNewSingleEvent().getStartDate().getTime();
            long endDate = activity2.getNewSingleEvent().getEndDate().getTime();
            long compteur = startDate;
            while(compteur < endDate) {
                Event temp = new Event(activity2.getNewSingleEvent().getName(), activity2.getNewSingleEvent().getStartDate(), activity2.getNewSingleEvent().getEndDate(),
                        activity2.getNewSingleEvent().getLocation(), activity2.getNewSingleEvent().getDescription(), compteur);
                compactCalendarView.addEvent(temp);
                compteur += 1000*60*60*24;
            }
        }*/

        if(activity2.getNewSingleEvent().getStartDate() != activity2.getNewSingleEvent().getEndDate()){
            Event temp = new Event(activity2.getNewSingleEvent().getName(), activity2.getNewSingleEvent().getEndDate(), activity2.getNewSingleEvent().getEndDate(),
                    activity2.getNewSingleEvent().getLocation(), activity2.getNewSingleEvent().getDescription(), activity2.getNewSingleEvent().getEndDate().getTime());
            compactCalendarView.addEvent(temp);
        }
        compactCalendarView.addEvent(activity2.getNewSingleEvent());



        loadEvents();
        loadEventsForYear(2017);
        compactCalendarView.invalidate();

        logEventsByMonth(compactCalendarView);

        // below line will display Sunday as the first day of the week
        // compactCalendarView.setShouldShowMondayAsFirstDay(false);

        // disable scrolling calendar
        // compactCalendarView.shouldScrollMonth(false);

        // show days from other months as greyed out days
        // compactCalendarView.displayOtherMonthDays(true);

        // show Sunday as first day of month
        // compactCalendarView.setShouldShowMondayAsFirstDay(false);

        //set initial title
        toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        toolbar.setTitle(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));


        //set title on calendar scroll
        compactCalendarView.setListener(new CalendarView.CalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                toolbar.setTitle(dateFormatForMonth.format(dateClicked));
                List<Event> bookingsFromMap = compactCalendarView.getEvents(dateClicked);
                Log.d(TAG, "inside onclick " + dateFormatForDisplaying.format(dateClicked));
                if (bookingsFromMap != null) {
                    Log.d(TAG, bookingsFromMap.toString());
                    mutableBookings.clear();
                    for (Event booking : bookingsFromMap) {
                        mutableBookings.add(((String) booking.getName() + " (" + booking.getLocation() + ") " + " : " + booking.getDescription()));
                    }
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                toolbar.setTitle(dateFormatForMonth.format(firstDayOfNewMonth));
            }
        });

        showPreviousMonthBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compactCalendarView.showPreviousMonth();
            }
        });

        showNextMonthBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compactCalendarView.showNextMonth();
            }
        });

        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(getActivity(), EventActivity.class);
                    startActivity(intent);
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        addGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(getActivity(), GroupPageActivity.class);
                    startActivity(intent);
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        compactCalendarView.setAnimationListener(new CalendarView.CompactCalendarAnimationListener() {
            @Override
            public void onOpened() {
            }

            @Override
            public void onClosed() {
            }
        });


        // uncomment below to show indicators above small indicator events
        // compactCalendarView.shouldDrawIndicatorsBelowSelectedDays(true);

        // uncomment below to open onCreate
        //openCalendarOnCreate(v);

        return v;
    }



    private void openCalendarOnCreate(View v) {
        final RelativeLayout layout = (RelativeLayout)v.findViewById(R.id.main_content);
        ViewTreeObserver vto = layout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < 16) {
                    layout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                compactCalendarView.showCalendarWithAnimation();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        // Set to current day on resume to set calendar to latest day
        // toolbar.setTitle(dateFormatForMonth.format(new Date()));
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


    private void logEventsByMonth(CalendarView compactCalendarView) {
        currentCalender.setTime(new Date());
        currentCalender.set(Calendar.DAY_OF_MONTH, 1);
        currentCalender.set(Calendar.MONTH, Calendar.AUGUST);
        List<String> dates = new ArrayList<>();
        for (Event e : compactCalendarView.getEventsForMonth(new Date())) {
            dates.add(dateFormatForDisplaying.format(e.getTimeInMillis()));
        }
        Log.d(TAG, "Events for Aug with simple date formatter: " + dates);
        Log.d(TAG, "Events for Aug month using default local and timezone: " + compactCalendarView.getEventsForMonth(currentCalender.getTime()));
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

            compactCalendarView.addEvents(events);
        }
    }

    public List<Event> getAllEvents(){
        return compactCalendarView.getAllEvents();
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