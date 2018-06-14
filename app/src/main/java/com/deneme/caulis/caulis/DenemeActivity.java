package com.deneme.caulis.caulis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.deneme.caulis.caulis.classes.CaulisClasses;
import com.deneme.caulis.caulis.classes.CaulisList;
import com.deneme.caulis.caulis.classes.CaulisMessage;
import com.deneme.caulis.caulis.model.ConnectorInterface;
import com.deneme.caulis.caulis.model.firebase.FirebaseConnector;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DenemeActivity extends AppCompatActivity {

    private TextView denemeEkrani;
    private EditText denemeMessage;
    private DatabaseReference mPostReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deneme);

        final ConnectorInterface connector = FirebaseConnector.getInstance();


        this.denemeEkrani = findViewById(R.id.denemeEkrani);
        this.denemeMessage = findViewById(R.id.denemeMessage);

        findViewById(R.id.denemeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageContent = denemeMessage.getText().toString();
                CaulisMessage message = new CaulisMessage(messageContent);
                connector.sendMessage(message);
            }
        });


        //TODO:Taşınacak!!
        /*
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<CaulisMessage> list = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String mid = ds.getKey();
                    JSONObject o = null;
                    CaulisMessage m = null;
                    try {
                        o = new JSONObject(ds.getValue().toString());
                        Log.d("deneme","aşama 1 geçti");
                        m = new CaulisMessage(o);
                        Log.d("deneme","aşama 2 geçti:"+o);
                        Log.d("deneme","ananı eşşekler tepsin vol.2");

                    } catch (JSONException e) {
                        Log.d("deneme","patladı1");
                        e.printStackTrace();
                    }
                    if (m != null){
                        list.add(m);
                        ((TextView)findViewById(R.id.denemeEkrani)).setText(m.getMessage());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        */
        CaulisList list = new CaulisList();
        list.setMessageListener(new CaulisList.CaulisMessageListener() {
            @Override
            public void newObjectArrived(CaulisMessage c) {
                ((TextView)findViewById(R.id.denemeEkrani)).setText(c.getMessage());
            }
        });
        connector.setMessageListener(list,"1");

        CaulisList list2 = new CaulisList();
        connector.setGroupListener(list2);

    }

}
