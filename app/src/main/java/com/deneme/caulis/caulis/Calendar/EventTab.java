package com.deneme.caulis.caulis.Calendar;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.deneme.caulis.caulis.R;
import com.deneme.caulis.caulis.classes.CaulisEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static android.content.ContentValues.TAG;

public class EventTab extends Fragment {

    private CaulisEvent eventsContainer = new CaulisEvent(Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault()));

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.event_tab,container,false);

        final List<String> mutableBookings = new ArrayList<>();
        final ListView bookingsListView = (ListView) v.findViewById(R.id.bookings_listview);
        final ArrayAdapter adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mutableBookings);
        final CalendarActivity2 activity2 = (CalendarActivity2) getActivity();

        bookingsListView.setAdapter(adapter);
        List<Event> bookingsFromMap = activity2.getNewEvent();



        if(!bookingsFromMap.isEmpty()) {
            //mutableBookings.clear();
            for (Event booking : bookingsFromMap) {
                if(booking.getData() != null){
                    eventsContainer.addEvent(booking);
                    mutableBookings.add((String) booking.getData() + " - " + booking.getName() + " (" + booking.getLocation() + ") " + " : " + booking.getDescription());
                }

            }
            adapter.notifyDataSetChanged();
        }

        return v;
    }
}
