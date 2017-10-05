package com.example.anmol.events.Events;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
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
import android.view.animation.AlphaAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.anmol.events.Adapter.RecyclerAllEvents;
import com.example.anmol.events.Adapter.RecyclerRegEvents;
import com.example.anmol.events.Data.EventsAll;
import com.example.anmol.events.Data.EventsRegistered;
import com.example.anmol.events.Data.login;
import com.example.anmol.events.Login;
import com.example.anmol.events.MainActivity;
import com.example.anmol.events.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class RegisteredEvents extends AppCompatActivity {

    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RelativeLayout relativeLayout;
    RecyclerView rv;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    ImageView imageView;

    int i=0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Login First if not loggedIn
        final login l = new login(RegisteredEvents.this);
        l.Login(new login.AsyncCallback() {
            @Override
            public void onSuccess(JSONObject jsonObject) throws JSONException {

                boolean LoggedIn = jsonObject.getBoolean("loggedIn");

                if (!LoggedIn) {
                    Intent i = new Intent(RegisteredEvents.this, Login.class);
                    startActivity(i);
                    //finishing the activity
                    RegisteredEvents.this.finish();
                }

            }
        });


        setContentView(R.layout.registeredevents);

        final Timer timer = new Timer();

        initViews();

        setSupportActionBar(toolbar);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            int scroll = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (scroll==-1){
                    scroll=appBarLayout.getTotalScrollRange();
                }

                if (verticalOffset * -1 >= scroll-100 ){
                    collapsingToolbarLayout.setTitle("Registered Events");
                    collapsingToolbarLayout.setCollapsedTitleTextColor(Color.BLACK);
                }

                else{
                    collapsingToolbarLayout.setTitle("Registered Events");
                    collapsingToolbarLayout.setExpandedTitleTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
                    collapsingToolbarLayout.setExpandedTitleGravity(Gravity.CENTER);
                }

            }
        });


        login l1= new login(RegisteredEvents.this);

        l1.Login(new login.AsyncCallback() {
            @Override
            public void onSuccess(JSONObject jsonObject) throws JSONException {

                final boolean LoggedIn = jsonObject.getBoolean("loggedIn");

                // if LoggedIn is true
                if (LoggedIn){

                    EventsRegistered eventsRegistered = new EventsRegistered(RegisteredEvents.this);
                    eventsRegistered.getData(new EventsRegistered.AsyncCallback() {
                        @Override
                        public void onSuccess(final List<List<String>> result, final List<Boolean> result1) {

                            if (result.isEmpty()){
                                rv.setVisibility(View.GONE);
                                relativeLayout.setVisibility(View.VISIBLE);
                                Picasso.with(getApplicationContext()).load("http://orientindia.com/admin//130/evt_photo/4_event_marketing.jpg").fit().into(imageView);
                            }
                            else{
                                relativeLayout.setVisibility(View.GONE);
                                rv.setVisibility(View.VISIBLE);
                                layoutManager = new LinearLayoutManager(RegisteredEvents.this);
                                rv.setLayoutManager(layoutManager);
                                adapter = new RecyclerRegEvents(LoggedIn, RegisteredEvents.this, result.get(0), result.get(1), result.get(2), result.get(3), result.get(4), result1);
                                rv.setAdapter(adapter);


                                timer.scheduleAtFixedRate(new TimerTask() {
                                    @Override
                                    public void run() {


                                        RegisteredEvents.this.runOnUiThread(new Runnable() {
                                            public void run() {

                                                if (i < result.get(0).size()) {

                                                    Picasso.with(getApplicationContext()).load(result.get(0).get(i)).fit().into(imageView);

                                                    ScaleAnimation scaleAnimation=new ScaleAnimation(0,1,0,1);
                                                    scaleAnimation.setDuration(400);

                                                    scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

                                                    imageView.startAnimation(scaleAnimation);

                                                    i++;

                                                } else if (i == result.get(0).size()) {
                                                    i = 0;
                                                }


                                            }
                                        });
                                    }
                                }, 0, 3000);

                            }
                        }
                    });

                }

                else{

                    Intent i=new Intent(RegisteredEvents.this,Login.class);
                    startActivity(i);
                    RegisteredEvents.this.finish();

                }


            }
        });
    }

    //initialize views
    public void initViews(){
        relativeLayout= (RelativeLayout) findViewById(R.id.relRegisterd);
        toolbar = (Toolbar) findViewById(R.id.tooolbarRegEve);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarregeve);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsinggToolbarregEve);
        rv = (RecyclerView) findViewById(R.id.recyclerRegEvents);
        imageView = (ImageView) findViewById(R.id.imageRegEventsTop);
    }
}
