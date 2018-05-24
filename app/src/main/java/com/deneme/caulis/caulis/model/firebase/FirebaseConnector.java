package com.deneme.caulis.caulis.model.firebase;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.deneme.caulis.caulis.model.ConnectorInterface;
import com.deneme.caulis.caulis.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseConnector implements ConnectorInterface {

    private static FirebaseConnector INSTANCE = null;


    private FirebaseAuth mAuth;
    //private Context context;
    private Context pageContext;


    public Context getPageContext() {
        return pageContext;
    }


    public static FirebaseConnector getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FirebaseConnector();
        }
        return INSTANCE;
    }


    private FirebaseConnector() {
        // Connect to Firebase
        this.mAuth = FirebaseAuth.getInstance();
        //FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    //@TODO: Wrapper class araştır amg
    //final değişken inner classta stringlerle editlendi
    public void deneme(){
        final int a[] = { 5};
        Log.d("deneme", "deneme1:"+((Integer)a[0]).toString());
        class innerClass{
            public innerClass(){ }
            public void innerDeneme(){
                a[0] = 2;
            }
        }
        innerClass i = new innerClass();
        i.innerDeneme();
        Log.d("deneme", "deneme2:"+((Integer)a[0]).toString());
    }

    @Override
    public User login(String login, final String password, final Activity activity) {
// mAuth.createUserWithEmailAndPassword(email, password)...
        //Executora dikkat et!!!!!
        deneme();//gidici

        mAuth.signInWithEmailAndPassword(login, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("SignIn", "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();

                    Log.d("Connector", "Login successful.");
                    //updating user data
                }else{
                    // If sign in fails, display a message to the user.
                    Log.w("SignIn", "signInWithEmail:failure", task.getException());
                    Toast.makeText(activity.getBaseContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                    //updateUI(null);
                }
            }
        });
        return null;
    }

    @Override
    public boolean register(String login, String password, final Activity activity) {
// mAuth.createUserWithEmailAndPassword(email, password)...
                //Executora dikkat et!!!!!
        mAuth.createUserWithEmailAndPassword(login, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Connector", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Connector", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(activity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });

        return false;
    }


}
