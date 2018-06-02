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

    private Button myProfileButton;
    private Button logoutButton;
    private Button myCalendarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myProfileButton = findViewById(R.id.MainPage_ProfileButton);
        myProfileButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // startActivity(new Intent(MainPageActivity.this, MyProfileActivity.class));

                /*try {
                    Intent intent = new Intent(MainPageActivity.this, MyProfileActivity.class);
                    startActivity(intent);
                } catch(Exception e){
                    e.printStackTrace();
                }*/

            }
        });

        myCalendarButton = findViewById(R.id.MainPage_CalendarButton);
        myCalendarButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               // startActivity(new Intent(MainPageActivity.this, CalendarActivity.class));

                try {
                    Intent intent = new Intent(MainPageActivity.this, CalendarActivity.class);
                    startActivity(intent);
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        logoutButton = findViewById(R.id.MainPage_LogoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                 // startActivity(new Intent(MainPageActivity.this, MainActivity.class));

                try {
                    Intent intent = new Intent(MainPageActivity.this, MainActivity.class);
                    startActivity(intent);
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });




    }

}
