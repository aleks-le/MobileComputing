package com.deneme.caulis.caulis.Calendar;

import android.content.Intent;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.deneme.caulis.caulis.R;
import com.deneme.caulis.caulis.classes.CaulisEvent;
import com.deneme.caulis.caulis.model.ConnectorInterface;
import com.deneme.caulis.caulis.model.firebase.FirebaseConnector;

import java.sql.Time;
import java.util.Date;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ConnectorInterface connector = FirebaseConnector.getInstance();


        Button saveEventButton = (Button) findViewById(R.id.saveEventButton);
        saveEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ((TextView) findViewById(R.id.eventName)).getText().toString();
                Date startDate = (Date) ((TextView) findViewById(R.id.eventStartDate)).getText();
                Date endDate = (Date) ((TextView) findViewById(R.id.eventEndDate)).getText();
                Time startTime = (Time) ((TextView) findViewById(R.id.eventStartTime)).getText();
                Time endTime = (Time) ((TextView) findViewById(R.id.eventEndTime)).getText();
                String location = ((TextView) findViewById(R.id.eventLocation)).getText().toString();
                String description = ((TextView) findViewById(R.id.eventDescription)).getText().toString();
                int numberOfPeopleAllowed = Integer.parseInt(((TextView) findViewById(R.id.eventNumberOfPeopleAllowed)).getText().toString());
                CaulisEvent newEvent = new CaulisEvent(name, startDate, endDate, startTime, endTime, location, description, numberOfPeopleAllowed);
            }
        });

        Button goBackToCalendarButton = (Button) findViewById(R.id.goBackToCalendarButton);
        goBackToCalendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(EventActivity.this, CalendarActivity.class);
                    startActivity(intent);
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });


    }
}