package com.example.project.coursepicker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Profile touch listener
        LinearLayout l1 = findViewById(R.id.linearLayout);
        l1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.isActivated())
                    v.setActivated(false);
                else
                    v.setActivated(true);

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    // TODO: activity intent
                    return true;
                }
                return false;
            }
        });

        // Calendar touch listener
        LinearLayout l2 = findViewById(R.id.linearLayout2);
        l2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.isActivated())
                    v.setActivated(false);
                else
                    v.setActivated(true);

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    //TODO: update this with actual calendar
                    return true;
                }
                return false;
            }
        });

        // Courses touch listener
        LinearLayout l3 = findViewById(R.id.linearLayout3);
        l3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.isActivated())
                    v.setActivated(false);
                else
                    v.setActivated(true);

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Intent intent = new Intent(v.getContext(), CoursesActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // prevent multiple activities
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        // Options touch listener
        LinearLayout l4 = findViewById(R.id.linearLayout4);
        l4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.isActivated())
                    v.setActivated(false);
                else
                    v.setActivated(true);

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    // TODO: activity intent
                    return true;
                }
                return false;
            }
        });
    }

}
