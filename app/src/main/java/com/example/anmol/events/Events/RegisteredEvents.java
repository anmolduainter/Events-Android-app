package com.example.anmol.events.Events;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.anmol.events.Adapter.RecyclerAllEvents;
import com.example.anmol.events.Adapter.RecyclerRegEvents;
import com.example.anmol.events.Data.EventsAll;
import com.example.anmol.events.Data.EventsRegistered;
import com.example.anmol.events.Data.login;
import com.example.anmol.events.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by anmol on 1/8/17.
 */

public class RegisteredEvents extends AppCompatActivity {

    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView rv;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeredevents);

        toolbar= (Toolbar) findViewById(R.id.tooolbarRegEve);
        appBarLayout= (AppBarLayout) findViewById(R.id.appBarregeve);
        collapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsinggToolbarregEve);
        rv= (RecyclerView) findViewById(R.id.recyclerRegEvents);

        setSupportActionBar(toolbar);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            int scroll =-1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (scroll==-1){
                    scroll=appBarLayout.getTotalScrollRange();
                }

                if (verticalOffset * -1 >= scroll-150 ){
                    collapsingToolbarLayout.setTitle("RegisteredEvents");
                    collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

                }

                else{

                    collapsingToolbarLayout.setTitle(" ");

                }

            }
        });

        EventsRegistered eventsRegistered=new EventsRegistered(RegisteredEvents.this);
        eventsRegistered.getData(new EventsRegistered.AsyncCallback() {
            @Override
            public void onSuccess(List<List<String>> result) {


                layoutManager=new LinearLayoutManager(RegisteredEvents.this);
                rv.setLayoutManager(layoutManager);
                adapter=new RecyclerRegEvents(RegisteredEvents.this,result.get(0),result.get(1),result.get(2),result.get(3),result.get(4));
                rv.setAdapter(adapter);


            }
        });
    }
}
