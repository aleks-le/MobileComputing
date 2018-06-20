package com.deneme.caulis.caulis.Group;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.deneme.caulis.caulis.Calendar.CalendarActivity;
import com.deneme.caulis.caulis.Calendar.CalendarActivity2;
import com.deneme.caulis.caulis.R;


public class GroupActivity extends AppCompatActivity implements View.OnClickListener {

    Button saveGroupButton, goBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        saveGroupButton = (Button) findViewById(R.id.saveEventButton);
        saveGroupButton.setOnClickListener(this);
        goBackButton = (Button) findViewById(R.id.goBackToCalendarButton);
        goBackButton.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.saveGroupButton:
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.goBackButton:
                try {
                    Intent intent = new Intent(GroupActivity.this, CalendarActivity2.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
