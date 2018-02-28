package com.example.project.coursepicker;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CoursesActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, String> listDataChild;

    Spinner ddTerm;
    ArrayAdapter<CharSequence> termAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ddTerm = findViewById(R.id.spinner);
        termAdapter = ArrayAdapter.createFromResource(this, R.array.term, android.R.layout.simple_spinner_item);
        termAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddTerm.setAdapter(termAdapter);

        expListView = (ExpandableListView) findViewById(R.id.exp);

        ddTerm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                // build courses list
                prepareListData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });

        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /*
     * Build the courses list
     */
    private void prepareListData() {

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, String>();

        //Global variables (for DB connection)
        AppData appData = (AppData)getApplication();

        Query term;

        //Set up DB connection
        appData.DB = FirebaseDatabase.getInstance();
        appData.firebaseReference = appData.DB.getReference("Course");


        if (ddTerm.getSelectedItem().equals("-- All --"))
            term = appData.firebaseReference.orderByValue();
        else
            //query DB for courses listed in the specified term (according to user drop down selection)
            term = appData.firebaseReference.orderByChild("term").equalTo(ddTerm.getSelectedItem().toString());

        //set up listener to pull course data from DB
        term.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot course : dataSnapshot.getChildren()) {

                    Course courses = course.getValue(Course.class);

                    listDataHeader.add(courses.name);
                    listDataChild.put(listDataHeader.get(i), "Days: " + courses.classDays +
                                                           "\nTime: " + courses.classTime +
                                                           "\nTerm: " + courses.term +
                                                            "\nPrerequisites: " + courses.prerequisites);
                    i++;
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });
    }
}