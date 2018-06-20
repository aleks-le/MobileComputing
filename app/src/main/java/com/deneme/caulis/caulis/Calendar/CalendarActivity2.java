package com.deneme.caulis.caulis.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.deneme.caulis.caulis.R;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CalendarActivity2 extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager pager;
    private ViewTabAdapter adapter;
    private SlidingTabLayout tabs;
    private CharSequence titles[]= {"Home","Events"};
    private int numberOfTabs = 2;
    private List<Event> newEvent = new ArrayList<Event>();
    private Event newSingleEvent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar2);

        Intent intent = getIntent();
        if(intent != null){
            String name = null;
            Date startDate = null;
            Date endDate = null;
            Time startTime = null;
            Time endTime = null;
            String location = null;
            String description = null;
            int numberOfPeopleAllowed = 0;
            long timeInMillis = 0;

            if(intent.hasExtra("Name")){
                name = intent.getStringExtra("Name");
            }
            if(intent.hasExtra("StarDate")){
                startDate = (Date)intent.getSerializableExtra("StartDate");
            }
            if(intent.hasExtra("EndDate")){
                endDate = (Date)intent.getSerializableExtra("EndDate");
            }
            if(intent.hasExtra("Location")){
                location = intent.getStringExtra("Location");
            }
            if(intent.hasExtra("Description")){
                description = intent.getStringExtra("Description");
            }
            if(intent.hasExtra("TimeInMillis")){
                timeInMillis = intent.getLongExtra("TimeInMillis",0);
            }


            newSingleEvent = new Event(name, startDate, endDate, location, description, timeInMillis);
            newEvent.add(newSingleEvent);

        }


        /*toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);*/

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewTabAdapter(getSupportFragmentManager(), titles, numberOfTabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.black);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public List<Event> getNewEvent(){return this.newEvent;}
    public Event getNewSingleEvent(){return  this.newSingleEvent;}
}
