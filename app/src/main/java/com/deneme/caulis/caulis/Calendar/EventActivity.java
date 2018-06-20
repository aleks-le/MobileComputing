package com.deneme.caulis.caulis.Calendar;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.CalendarContract;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.time.format.DateTimeFormatter;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.deneme.caulis.caulis.R;
import com.deneme.caulis.caulis.model.ConnectorInterface;
import com.deneme.caulis.caulis.model.firebase.FirebaseConnector;

import java.sql.Time;
import java.util.Date;

public class EventActivity extends AppCompatActivity implements View.OnClickListener{

    Button saveEventButton, goBackToCalendarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ConnectorInterface connector = FirebaseConnector.getInstance();
        saveEventButton = (Button) findViewById(R.id.saveEventButton);
        saveEventButton.setOnClickListener(this);
        goBackToCalendarButton = (Button) findViewById(R.id.goBackToCalendarButton);
        goBackToCalendarButton.setOnClickListener(this);

/*
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

                ContentValues cv = new ContentValues();
                cv.put(CalendarContract.Events.TITLE, name);
                cv.put(CalendarContract.Events.DESCRIPTION, description);
                cv.put(CalendarContract.Events.EVENT_LOCATION, location);
                //cv.put(CalendarContract.Events.DTSTART, startDate);

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
        });*/


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.saveEventButton:
                /*if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }*/
                Calendar cal = Calendar.getInstance();
                String name = ((TextView) findViewById(R.id.eventName)).getText().toString();
                String s = (((TextView) findViewById(R.id.eventStartDate)).getText().toString());
                SimpleDateFormat d = new SimpleDateFormat("MM/dd/yyyy");
                Date startDate = new Date();
                try{
                    startDate = d.parse(s);
                    Log.d("mDebug","patlamadi ya la");
                }catch(ParseException e){
                    Log.d("mDebug","patliyor aq:"+s);
                }
                String t = (((TextView) findViewById(R.id.eventEndDate)).getText().toString());
                Date endDate = new Date();
                try{
                    endDate = d.parse(t);
                    Log.d("mDebug","patlamadi ya la");
                }catch(ParseException e){
                    Log.d("mDebug","patliyor aq:"+s);
                }
                String location = ((TextView) findViewById(R.id.eventLocation)).getText().toString();
                String description = ((TextView) findViewById(R.id.eventDescription)).getText().toString();
                long timeInMillis = startDate.getTime();

                try{
                    Intent intent = new Intent(EventActivity.this, CalendarActivity2.class);
                    intent.putExtra("Name", name);
                    intent.putExtra("StartDate", startDate);
                    intent.putExtra("EndDate", endDate);
                    intent.putExtra("Location", location);
                    intent.putExtra("Description", description);
                    intent.putExtra("TimeInMillis", timeInMillis);
                    startActivity(intent);
                } catch(Exception e) {
                    e.printStackTrace();
                }

                //Toast.makeText(this, "Event is sucessfully added.", Toast.LENGTH_SHORT).show();

                /*
                long startTimes = cal.getTimeInMillis();
                long endTimes = cal.getTimeInMillis() + 60*60*1000;
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTimes);
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,endTimes);
                intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
                intent.putExtra(CalendarContract.Events.TITLE,"bkabka");
                intent.putExtra(CalendarContract.Events.DESCRIPTION,"bfldk");
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION,"sd");

                Toast.makeText(this, "Event is sucessfully added.", Toast.LENGTH_SHORT).show();
                startActivity(intent);*/

                break;

            case R.id.goBackToCalendarButton:
                try {
                    Intent intent2 = new Intent(EventActivity.this, CalendarActivity2.class);
                    startActivity(intent2);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                break;

        }
    }
}