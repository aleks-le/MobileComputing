package com.deneme.caulis.caulis.Calendar;

//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.deneme.caulis.caulis.R;

import java.util.ArrayList;
import java.util.List;

public class EventTab extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.event_tab,container,false);

        final List<String> mutableBookings = new ArrayList<>();
        final ListView bookingsListView = (ListView) v.findViewById(R.id.bookings_listview);
        final ArrayAdapter adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mutableBookings);
        bookingsListView.setAdapter(adapter);

        return v;
    }

}
