package com.deneme.caulis.caulis.Calendar;
//package ganeshannt.calendarview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.Toast;

import com.deneme.caulis.caulis.MainActivity;
import com.deneme.caulis.caulis.R;
import com.deneme.caulis.caulis.Group.GroupActivity;

public class CalendarActivity extends AppCompatActivity {

    private  static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;
    private Button addEventButton;
    private Button goToEventListButton;
    private CheckBox participateEvent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
                String date = year + "/" + month + "/"+ dayOfMonth ;
                Log.d(TAG, "onSelectedDayChange: yyyy/mm/dd:" + date);
                Intent intent = new Intent(CalendarActivity.this, DayActivity.class);
                intent.putExtra("date",date);
                startActivity(intent);

            }
        });


        Button addEventButton = (Button) findViewById(R.id.addEventButton);
        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(CalendarActivity.this, EventActivity.class);
                    startActivity(intent);
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        //we want to see all the event of the group or the event which you participate
        Button listEventButton = (Button) findViewById(R.id.goToEventListButton);
        listEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(CalendarActivity.this, EventActivity.class);
                    startActivity(intent);
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        Button createGroupButton = (Button) findViewById(R.id.createGroupButton);
        createGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(CalendarActivity.this, GroupActivity.class);
                    startActivity(intent);
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    public void addListenerOnCheckBox() {
        participateEvent = (CheckBox) findViewById(R.id.participateEvent);
        participateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CheckBox) view).isChecked()) {
                    Toast.makeText(CalendarActivity.this, "You only want to see the registrated events", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
