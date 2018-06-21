package com.deneme.caulis.caulis.Group;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.deneme.caulis.caulis.R;
import com.deneme.caulis.caulis.classes.CaulisGroup;
import com.deneme.caulis.caulis.classes.CaulisList;
import com.deneme.caulis.caulis.model.ConnectorInterface;
import com.deneme.caulis.caulis.model.firebase.FirebaseConnector;

import java.util.ArrayList;

public class GroupPageActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grouppage);
        final ConnectorInterface connector = FirebaseConnector.getInstance();

        final ArrayList<CaulisGroup> list = new ArrayList<CaulisGroup>();
        final GroupAdapter adapter = new GroupAdapter(list,this);


        ListView lView = (ListView)findViewById(R.id.groups_view);
        lView.setAdapter(adapter);

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
