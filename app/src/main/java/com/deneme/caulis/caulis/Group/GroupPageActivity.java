package com.deneme.caulis.caulis.Group;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.deneme.caulis.caulis.Calendar.CalendarActivity2;
import com.deneme.caulis.caulis.MainActivity;
import com.deneme.caulis.caulis.R;
import com.deneme.caulis.caulis.SignInActivity;
import com.deneme.caulis.caulis.classes.CaulisGroup;
import com.deneme.caulis.caulis.classes.CaulisList;
import com.deneme.caulis.caulis.model.ConnectorInterface;
import com.deneme.caulis.caulis.model.firebase.FirebaseConnector;

import java.util.ArrayList;

public class GroupPageActivity extends AppCompatActivity {

    private Button createGroupButton, goBackToCalendarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grouppage);

        final ConnectorInterface connector = FirebaseConnector.getInstance();
        final ArrayList<CaulisGroup> list = new ArrayList<CaulisGroup>();
        final GroupAdapter adapter = new GroupAdapter(list,this);

        ListView lView = (ListView)findViewById(R.id.groups_view);
        lView.setAdapter(adapter);

        createGroupButton = (Button) findViewById(R.id.createGroupButton4);
        createGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(GroupPageActivity.this, GroupActivity.class);
                    startActivity(intent);
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        goBackToCalendarButton = (Button) findViewById(R.id.goBackButton4);
        goBackToCalendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(GroupPageActivity.this, CalendarActivity2.class);
                    startActivity(intent);
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        CaulisList list2 = new CaulisList();
        list2.setGroupListener(new CaulisList.CaulisGroupListener() {
            @Override
            public void newObjectArrived(CaulisGroup c) {
                //Log.d("deneme","yeni grup amg hadi bakalım:"+c.getName());
                list.add(c);
                adapter.notifyDataSetChanged();
            }
        });
        connector.setGroupListener(list2);




    }
}
