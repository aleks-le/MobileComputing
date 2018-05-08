package com.deneme.caulis.caulis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.deneme.caulis.caulis.model.CalendarInterface;
import com.deneme.caulis.caulis.model.firebase.FirebaseConnector;

public class DenemeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deneme);

        final CalendarInterface connector = FirebaseConnector.getInstance();



    }
}
