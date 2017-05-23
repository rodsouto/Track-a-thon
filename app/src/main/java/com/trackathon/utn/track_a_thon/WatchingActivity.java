package com.trackathon.utn.track_a_thon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.trackathon.utn.track_a_thon.firebase.Firebase;


public class WatchingActivity extends AppCompatActivity {

    RecyclerView racesWatchers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watching);
        setComponentsBinding();
    }

    private void setComponentsBinding() {
        LinearLayoutManager racesLayout = new LinearLayoutManager(getApplicationContext());

        racesWatchers = (RecyclerView)findViewById(R.id.races);
        racesWatchers.setHasFixedSize(true);
        racesWatchers.setLayoutManager(racesLayout);

        Firebase.allRaces(races -> {
            RecycleViewRaceAdapter adapter = new RecycleViewRaceAdapter(races);
            racesWatchers.setAdapter(adapter);
        });
    }

}
