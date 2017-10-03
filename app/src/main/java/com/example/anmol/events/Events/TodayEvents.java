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
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.anmol.events.Adapter.RecyclerTodayEvents;
import com.example.anmol.events.Data.EventsToday;
import com.example.anmol.events.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TodayEvents extends AppCompatActivity {

    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RelativeLayout rel;
    RecyclerView rv;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    ImageView imageView;

    int i=0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.todayevents_main);

        final Timer timer = new Timer();

        rel= (RelativeLayout) findViewById(R.id.relToday);
        toolbar = (Toolbar) findViewById(R.id.tooolbarTodayEve1);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarTodayeve1);
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


        EventsToday eventsToday=new EventsToday(TodayEvents.this);

        eventsToday.getData(new EventsToday.AsyncCallback() {
            @Override
            public void onSuccess(final List<List<String>> list, boolean Today, boolean LoggedIn) {


                // if Today is true
                if (Today){
                    rel.setVisibility(View.GONE);
                    rv.setVisibility(View.VISIBLE);

                    layoutManager=new LinearLayoutManager(TodayEvents.this);
                    adapter=new RecyclerTodayEvents(LoggedIn,TodayEvents.this,list.get(0),list.get(1),list.get(2),list.get(3),list.get(4),list.get(7));
                    rv.setLayoutManager(layoutManager);
                    rv.setAdapter(adapter);

                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            TodayEvents.this.runOnUiThread(new Runnable() {
                                public void run() {

                                    if (i < list.get(0).size()) {

                                        Picasso.with(getApplicationContext()).load(list.get(0).get(i)).fit().into(imageView);

                                        ScaleAnimation scaleAnimation=new ScaleAnimation(0,1,0,1);
                                        scaleAnimation.setDuration(400);

                                        scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

                                        imageView.startAnimation(scaleAnimation);

                                        i++;

                                    } else if (i == list.get(0).size()) {
                                        i = 0;
                                    }
                                }
                            });
                        }
                    }, 0, 3000);
                }

                //false
                else{

                    Picasso.with(getApplicationContext()).load("http://orientindia.com/admin//130/evt_photo/4_event_marketing.jpg").fit().into(imageView);

                    rel.setVisibility(View.VISIBLE);
                    rv.setVisibility(View.GONE);

                }


            }
        });





    }
}

