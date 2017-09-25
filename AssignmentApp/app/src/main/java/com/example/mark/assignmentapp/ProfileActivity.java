package com.example.mark.assignmentapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;

    private DatabaseReference databaseReference;

    private EditText editTextName, editTextAddress, editJourneyStart, editJourneyEnd;
    private Button buttonSave;
    private Button buttonSaveTrip;
    private Button buttonDisplayTrips;
    ListView listViewJourney;

    List<JourneyInfo> journeyInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        listViewJourney = (ListView)findViewById(R.id.listViewJourney);
        buttonSave = (Button)findViewById(R.id.save_button);
        editTextName = (EditText)findViewById(R.id.name_edittext);
        editTextAddress = (EditText)findViewById(R.id.address_edittext);

        buttonSaveTrip = (Button)findViewById(R.id.save_trip_button);
        editJourneyStart = (EditText)findViewById(R.id.journey_start_edittext);
        editJourneyEnd = (EditText)findViewById(R.id.journey_end_edittext);

        buttonDisplayTrips = (Button)findViewById(R.id.display_trip_button);

        journeyInfoList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth == null) {
            startActivity(new Intent(this, LogInActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUserEmail = (TextView)findViewById(R.id.user_email);
        textViewUserEmail.setText("Welcome " + user.getEmail());


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInformation();
            }
        });

        buttonSaveTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserJourneyInformation();
            }
        });

        buttonDisplayTrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayJourneyInfo();
            }
        });
    }




    private void displayJourneyInfo() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                journeyInfoList.clear();

                for(DataSnapshot journeySnapshot : dataSnapshot.getChildren()) {
                    JourneyInfo journeyInfo = journeySnapshot.getValue(JourneyInfo.class);

                    journeyInfoList.add(journeyInfo);
                }

                JourneyList adaptor = new JourneyList(ProfileActivity.this, journeyInfoList);
                listViewJourney.setAdapter(adaptor);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
              Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void saveUserInformation(){
        String name = editTextName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();

        UserInformation userInformation = new UserInformation(name, address);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).push().setValue(userInformation);

        Toast.makeText(this, "Information Saved",  Toast.LENGTH_SHORT).show();
    }


    private void saveUserJourneyInformation() {
        String start = editJourneyStart.getText().toString().trim();
        String end = editJourneyEnd.getText().toString().trim();

        JourneyInfo journeyInfo = new JourneyInfo(start, end);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).push().setValue(journeyInfo);

        Toast.makeText(this, "Information Saved",  Toast.LENGTH_SHORT).show();
    }
}
