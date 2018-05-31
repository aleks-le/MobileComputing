package com.deneme.caulis.caulis;

/**
 * Created by Aleksandra on 31-May-18.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.deneme.caulis.caulis.Calendar.CalendarActivity;
import com.deneme.caulis.caulis.model.ConnectorInterface;
import com.deneme.caulis.caulis.model.User;
import com.deneme.caulis.caulis.model.firebase.FirebaseConnector;

public class MainPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

}
