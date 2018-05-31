package com.deneme.caulis.caulis.Calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.deneme.caulis.caulis.R;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Button saveEvent = (Button) findViewById(R.id.saveEvent);
        
    }


}
