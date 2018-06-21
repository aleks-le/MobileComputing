package com.deneme.caulis.caulis.Group;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.deneme.caulis.caulis.Calendar.CalendarActivity;
import com.deneme.caulis.caulis.Calendar.CalendarActivity2;
import com.deneme.caulis.caulis.R;
import com.deneme.caulis.caulis.classes.CaulisGroup;
import com.deneme.caulis.caulis.model.ConnectorInterface;
import com.deneme.caulis.caulis.model.firebase.FirebaseConnector;


public class GroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        final ConnectorInterface connector = FirebaseConnector.getInstance();


        ((Button)findViewById(R.id.groupActivity_saveGroupButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gName = ((EditText)findViewById(R.id.groupActivity_groupName)).getText().toString();
                String gDescription = ((EditText)findViewById(R.id.groupActivity_groupDescription)).getText().toString();
                CaulisGroup g = new CaulisGroup(gName, gDescription);
                Log.d("deneme","tıkladılar söndüm:"+gName+"::"+gDescription);
                connector.createGroup(g);
            }
        });

        ((Button)findViewById(R.id.groupActivity_goBackButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(GroupActivity.this, CalendarActivity2.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }








}
