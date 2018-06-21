package com.deneme.caulis.caulis.Group;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.deneme.caulis.caulis.R;
import com.deneme.caulis.caulis.classes.CaulisGroup;
import com.deneme.caulis.caulis.classes.CaulisList;
import com.deneme.caulis.caulis.classes.CaulisMessage;
import com.deneme.caulis.caulis.model.ConnectorInterface;
import com.deneme.caulis.caulis.model.firebase.FirebaseConnector;

public class GroupChatActivity extends AppCompatActivity {
    private CaulisGroup groupID;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupchat);
        final ConnectorInterface connector = FirebaseConnector.getInstance();
        final MessageAdapter messageAdapter = new MessageAdapter(GroupChatActivity.this.getApplicationContext());
        this.groupID = (CaulisGroup) getIntent().getExtras().getSerializable("group");
        //TODO: düzeltilecek amg




        ListView lView = (ListView)findViewById(R.id.messages_view);
        lView.setAdapter(messageAdapter);

        //this.groupID = list2.getLastGroup().getGroupID();

        CaulisList list = new CaulisList();
        list.setMessageListener(new CaulisList.CaulisMessageListener() {
            @Override
            public void newObjectArrived(CaulisMessage c) {
                Log.d("denemeChat",c.getMessage());
                messageAdapter.add(c);
                messageAdapter.notifyDataSetChanged();
            }
        });
        connector.setMessageListener(list,this.groupID.getGroupID());


        ((Button)findViewById(R.id.chatSendButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = ((EditText) findViewById(R.id.messageText)).getText().toString();
                ((EditText) findViewById(R.id.messageText)).setText("");
                CaulisMessage message = new CaulisMessage(text);
                //Log.d("denemeChat","id:"+GroupChatActivity.this.groupID.getGroupID());
                connector.sendMessage(message,GroupChatActivity.this.groupID.getGroupID());
            }
        });



    }
}
