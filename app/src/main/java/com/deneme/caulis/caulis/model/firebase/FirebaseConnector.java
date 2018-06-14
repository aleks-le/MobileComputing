package com.deneme.caulis.caulis.model.firebase;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.deneme.caulis.caulis.R;
import com.deneme.caulis.caulis.classes.CaulisEvent;
import com.deneme.caulis.caulis.classes.CaulisGroup;
import com.deneme.caulis.caulis.classes.CaulisList;
import com.deneme.caulis.caulis.classes.CaulisMessage;
import com.deneme.caulis.caulis.model.ConnectorInterface;
import com.deneme.caulis.caulis.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FirebaseConnector implements ConnectorInterface {
    /**Consts***********************************************/
    private static final int MAX_KEY_LENGTH = 50;
    //private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.,?!<> ;:#@-_|/()[]{}&%+$";
    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";



    /**Firebase variables***********************************/
    private static FirebaseConnector INSTANCE = null;
    private User user;


    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    //private Context context;
    private Context pageContext;


    public Context getPageContext() {
        return pageContext;
    }

    public void setUser(User user){
        this.user = user;
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
        this.mDatabase = FirebaseDatabase.getInstance().getReference();
        //FirebaseUser currentUser = mAuth.getCurrentUser();
    }


    @Override
    public User login(final String login, final String password, final Activity activity) {
// mAuth.createUserWithEmailAndPassword(email, password)...

        mAuth.signInWithEmailAndPassword(login, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("SignIn", "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    User u = new User(user.getEmail(), password);/**denenmedi*/
                    if (activity instanceof LoginCallback) {
                        ((LoginCallback) activity).userLoggedIn(u);
                    }
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
    public boolean register(String login, final String password, final Activity activity) {
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
                            User u = new User(user.getEmail(), password);//@TODO: düzenlenecek

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

    public String randomString(){
        int i;
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        for(i=0 ; i<this.MAX_KEY_LENGTH ; i++){
            randomStringBuilder.append(ALLOWED_CHARACTERS.charAt(generator.nextInt(ALLOWED_CHARACTERS.length())));
        }
        return randomStringBuilder.toString();
    }

    @Override
    public void sendMessage(CaulisMessage message) {
        message.setMessageID(randomString());
        mDatabase.child("messages").child("groups").child("1").child("groupMessages").child(message.getMessageTime()+"::"+message.getMessageID()).setValue(message);
    }

    @Override
    public void createGroup(CaulisGroup group) {
        group.setGroupID(randomString());
        mDatabase.child("messages").child("groups").child(group.getGroupID()).child("groupInfo").setValue(group);
    }

    @Override
    public void createEvent(CaulisEvent event) {

    }



    @Override
    public void setMessageListener(final CaulisList list, String groupID) {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = rootRef.child("messages").child("groups").child(groupID).child("groupMessages");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    JSONObject o = null;
                    CaulisMessage m = null;
                    try {
                        o = new JSONObject(ds.getValue().toString());
                        m = new CaulisMessage(o);

                    } catch (JSONException e) {
                        Log.d("MyDebug","Error occured in FirebaseConnector.java getMessageListener method inner try/catch");
                        Log.d("Debug","Error occured in FirebaseConnector.java getMessageListener method inner try/catch");
                        e.printStackTrace();
                    }
                    if (m != null){
                        list.madd(m);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        userRef.addListenerForSingleValueEvent(eventListener);
        userRef.addValueEventListener(eventListener);
    }

    @Override
    public void setGroupListener(final CaulisList list) {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = rootRef.child("messages").child("groups");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    JSONObject o = null;
                    CaulisGroup g = null;
                    try {
                        Log.d("deneme","group key:"+ds.getKey());
                        Log.d("deneme","group info:"+ds.getValue().toString());
                        o = new JSONObject(ds.getValue().toString());
                        Log.d("deneme","group:"+o);
                        //g = new CaulisMessage(o);

                    } catch (JSONException e) {
                        Log.d("MyDebug","Error occured in FirebaseConnector.java getGroupListener method inner try/catch");
                        Log.d("Debug","Error occured in FirebaseConnector.java getGroupListener method inner try/catch");
                        e.printStackTrace();
                    }
                    if (g != null){
                        Log.d("deneme","group:"+o);
                        //list.gadd(g);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        userRef.addListenerForSingleValueEvent(eventListener);
        userRef.addValueEventListener(eventListener);
    }
}
