package com.deneme.caulis.caulis.Calendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.deneme.caulis.caulis.MainActivity;
import com.deneme.caulis.caulis.R;

public class DayActivity extends AppCompatActivity {

    private TextView thedate;
    private Button goToCalendarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        thedate = (TextView) findViewById(R.id.date);

        Intent incoming = getIntent();
        String date = incoming.getStringExtra("date");
        thedate.setText(date);

        Button goToCalendarButton = (Button) findViewById(R.id.goToCalendarButton);
        goToCalendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(DayActivity.this, CalendarActivity.class);
                    startActivity(intent);
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}
