package com.example.project.coursepicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by christian on 2018-03-12.
 */

public class userProfile extends AppCompatActivity {

    DatabaseReference db_root, database_u_id, database_u_name, database_u_email;
    private TextView user_ID;
    private TextView user_nameView;
    private TextView user_ChangePW;
    private EditText user_Email;
    private EditText user_Phone;
    private Button user_Update;
    private Session session1;   //-------------------------is this correct way to call session ???


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        db_root = FirebaseDatabase.getInstance().getReference();
        database_u_id = db_root.child("Users").child("Ab123456");    //------------------need reference to session user
        //database_u_name = db_root.child("Users").child("Ab123456");
        //database_u_email = db_root.child("Users").child("Ab123456");

        user_ID = (TextView) findViewById(R.id.userID);
        user_nameView = (TextView) findViewById(R.id.userNameViewer);
        user_ChangePW = (TextView) findViewById(R.id.changePWBtn);
        user_Email = (EditText) findViewById(R.id.userEmail);
        user_Phone = (EditText) findViewById(R.id.userPhone);
        user_Update = (Button) findViewById(R.id.updateProfileBtn);


        user_ID.setText(database_u_id.toString());
        user_nameView.setText(database_u_id.child("Name").toString());
        user_Email.setText(database_u_id.child("Email").toString());
        user_Phone.setText(database_u_id.child("Phone").toString());


        startChangePassword(user_ChangePW);


        database_u_id.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot_1) {
                String u_id = dataSnapshot_1.getValue().toString();
                user_ID.setText(u_id);

                database_u_name = db_root.child("Users").child(session1.getID()).child("Name");  //--------------need reference to session user
                user_nameView = (TextView) findViewById(R.id.userNameViewer);

                database_u_name.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot_2) {
                        String u_name = dataSnapshot_2.getValue().toString();   //gets userName <------ need to add reference to "session user"
                        user_ID.setText(u_name);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }


    private void startChangePassword(View view){
        Intent intent = new Intent(this, changePassword.class);
        startActivity(intent);
    }

    private void updateProfileDetails(View view){

    }

}
