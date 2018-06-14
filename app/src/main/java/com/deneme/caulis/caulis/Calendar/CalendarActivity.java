package com.deneme.caulis.caulis.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import com.deneme.caulis.caulis.Group.GroupActivity;
import com.deneme.caulis.caulis.R;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;
    //private CheckBox participateEvent;
    Button addEventButton, goToEventListButton, addGroupButton;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
                month = month +1;
                String date = year + "/" + month + "/" + dayOfMonth;
                Log.d(TAG, "onSelectedDayChange: yyyy/mm/dd:" + date);
                Intent intent = new Intent(CalendarActivity.this, DayActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);

            }
        });

        addEventButton = (Button) findViewById(R.id.addEventButton);
        addEventButton.setOnClickListener(this);
        goToEventListButton = (Button) findViewById(R.id.goToEventListButton);
        goToEventListButton.setOnClickListener(this);
        addGroupButton = (Button) findViewById(R.id.addGroupButton);
        addGroupButton.setOnClickListener(this);


    }

    /*
    public void addListenerOnCheckBox() {
        participateEvent = (CheckBox) findViewById(R.id.participateEvent);
        participateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()) {
                    Toast.makeText(CalendarActivity.this, "You only want to see the registrated events", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    */


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addEventButton:
                try {
                    Intent intent = new Intent(CalendarActivity.this, EventActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.goToEventListButton:
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.addGroupButton:
                try {
                    Intent intent = new Intent(CalendarActivity.this, GroupActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

}


