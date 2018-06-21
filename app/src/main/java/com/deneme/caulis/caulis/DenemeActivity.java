package com.deneme.caulis.caulis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.deneme.caulis.caulis.Group.GroupChatActivity;
import com.deneme.caulis.caulis.Group.GroupPageActivity;
import com.deneme.caulis.caulis.Group.groupActivityInterface;
import com.deneme.caulis.caulis.classes.CaulisClasses;
import com.deneme.caulis.caulis.classes.CaulisGroup;
import com.deneme.caulis.caulis.classes.CaulisList;
import com.deneme.caulis.caulis.classes.CaulisMessage;
import com.deneme.caulis.caulis.model.ConnectorInterface;
import com.deneme.caulis.caulis.model.User;
import com.deneme.caulis.caulis.model.firebase.FirebaseConnector;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DenemeActivity extends AppCompatActivity{


    private TextView denemeEkrani;
    private EditText denemeMessage;
    private DatabaseReference mPostReference;
    private CaulisList list2;

    private CaulisGroup group;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deneme);

        final ConnectorInterface connector = FirebaseConnector.getInstance();


        this.denemeEkrani = findViewById(R.id.denemeEkrani);
        this.denemeMessage = findViewById(R.id.denemeMessage);

/*
        CaulisList list = new CaulisList();
        list.setMessageListener(new CaulisList.CaulisMessageListener() {
            @Override
            public void newObjectArrived(CaulisMessage c) {
                ((TextView)findViewById(R.id.denemeEkrani)).setText(c.getMessage());
            }
        });
        connector.setMessageListener(list,"1"); */

        list2 = new CaulisList();
        list2.setGroupListener(new CaulisList.CaulisGroupListener() {
            @Override
            public void newObjectArrived(CaulisGroup c) {
                Log.d("deneme","yeni grup amg hadi bakalım:"+c.getName());
                //this.group = c;
            }
        });
        connector.setGroupListener(list2);

        findViewById(R.id.denemeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageContent = denemeMessage.getText().toString();
                CaulisMessage message = new CaulisMessage(messageContent);
                connector.sendMessage(message,"1");
            }
        });

        ((Button)findViewById(R.id.tiklaButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User u = new User("aa@aa.aa","1","A","A");
                //TODO:chate git
                try {
                    Intent i = new Intent(DenemeActivity.this, GroupPageActivity.class);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });




    }


}
