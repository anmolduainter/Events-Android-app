package com.example.anmol.events.Events;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.anmol.events.Adapter.RecyclerRegEvents;
import com.example.anmol.events.Data.EventsRegistered;
import com.example.anmol.events.Data.login;
import com.example.anmol.events.Login;
import com.example.anmol.events.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TodayEvents extends AppCompatActivity {

    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView rv;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    ImageView imageView;

    int i=0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.registeredevents);

        final Timer timer = new Timer();

        toolbar = (Toolbar) findViewById(R.id.tooolbarTodayEve1);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarTodayeve);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.CollapToolBarTodayEve);
        rv = (RecyclerView) findViewById(R.id.recyclerTodayEvents1);

        imageView = (ImageView) findViewById(R.id.imageTodayEventsTop);

        setSupportActionBar(toolbar);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            int scroll = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (scroll == -1) {
                    scroll = appBarLayout.getTotalScrollRange();
                }

                if (verticalOffset * -1 >= scroll - 150) {
                    collapsingToolbarLayout.setTitle("Today Events");
                    collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

                } else {

                    collapsingToolbarLayout.setTitle("Today Events");
                    collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
                    collapsingToolbarLayout.setExpandedTitleGravity(Gravity.CENTER);

                }

            }
        });




    }
}

