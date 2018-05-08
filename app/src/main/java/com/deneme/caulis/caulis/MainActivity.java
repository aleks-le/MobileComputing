package com.deneme.caulis.caulis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.deneme.caulis.caulis.model.CalendarInterface;
import com.deneme.caulis.caulis.model.User;
import com.deneme.caulis.caulis.model.firebase.FirebaseConnector;

public class MainActivity extends AppCompatActivity {
    private Button signInButton;
    private Button logInButton;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CalendarInterface connector = FirebaseConnector.getInstance();

        logInButton = (Button) findViewById(R.id.logInButton);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = ((EditText)findViewById(R.id.emailTextBox)).getText().toString();
                password = ((EditText)findViewById(R.id.passwordTextBox)).getText().toString();
                connector.login(email,password);//dikkat!!
            }
        });
        signInButton = (Button) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = ((EditText)findViewById(R.id.emailTextBox)).getText().toString();
                password = ((EditText)findViewById(R.id.passwordTextBox)).getText().toString();
                connector.register(email,password);
            }
        });

        Button denemeButton = (Button) findViewById(R.id.denemeButton);
        denemeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MainActivity.this, DenemeActivity.class);
                    startActivity(intent);
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}
