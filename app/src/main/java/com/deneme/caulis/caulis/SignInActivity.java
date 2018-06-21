package com.deneme.caulis.caulis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.deneme.caulis.caulis.model.ConnectorInterface;
import com.deneme.caulis.caulis.model.User;
import com.deneme.caulis.caulis.model.firebase.FirebaseConnector;

public class SignInActivity extends AppCompatActivity implements ConnectorInterface.LoginCallback {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        final ConnectorInterface connector = FirebaseConnector.getInstance();


        ((Button) findViewById(R.id.saveAccount)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: kutucuklar boş mu kontrol et amg
                String email = ((EditText)findViewById(R.id.signInEmail)).getText().toString();
                String password1 = ((EditText)findViewById(R.id.signInPassword)).getText().toString();
                String password2 = ((EditText)findViewById(R.id.signInPasswordConfirm)).getText().toString();
                if(password1.equals(password2)){
                    connector.register(email,password1,SignInActivity.this);
                    String uName = ((EditText)findViewById(R.id.signInVorname)).getText().toString();
                    String uSurname = ((EditText)findViewById(R.id.signInName)).getText().toString();
                    User u = new User(email, password1, uName, uSurname);
                    connector.createUser(u);
                    try {
                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                        startActivity(intent);
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(SignInActivity.this, "Passwords are not matching.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ((Button) findViewById(R.id.signPageGoBackToLogIn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    startActivity(intent);
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void userLoggedIn(User user) {
        Log.d("test", "User " + user + " logged in.");
    }


}
