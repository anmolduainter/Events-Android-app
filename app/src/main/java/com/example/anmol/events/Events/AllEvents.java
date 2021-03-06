package com.example.anmol.events.Events;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import com.example.anmol.events.Adapter.RecyclerAllEvents;
import com.example.anmol.events.Data.EventsAll;
import com.example.anmol.events.Data.login;
import com.example.anmol.events.R;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class AllEvents extends AppCompatActivity {


    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    RecyclerView rv;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    ImageView imageView;
    int i=0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window w = getWindow();
//            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }

        setContentView(R.layout.allevents);


        toolbar= (Toolbar) findViewById(R.id.AllEventsToolBar);
        setSupportActionBar(toolbar);
        collapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsinggToolbarallEve);
        appBarLayout= (AppBarLayout) findViewById(R.id.appBaralleve);

        imageView= (ImageView) findViewById(R.id.imageAllEventsTop);

        rv= (RecyclerView) findViewById(R.id.recyclerAllEvents);

        final Timer timer=new Timer();


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {


            int scroll=-1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (scroll==-1){
                    scroll=appBarLayout.getTotalScrollRange();
                }

                if (verticalOffset * -1 >= scroll-100 ){
                    collapsingToolbarLayout.setTitle("Upcoming Events");
                    collapsingToolbarLayout.setCollapsedTitleTextColor(Color.GRAY);
                }

                else{
                    collapsingToolbarLayout.setTitle("Upcoming Events");
                    collapsingToolbarLayout.setExpandedTitleTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
                    collapsingToolbarLayout.setExpandedTitleGravity(Gravity.CENTER);
                }


            }
        });


        EventsAll allData=new EventsAll(AllEvents.this,"AllEvents");

        allData.getAll(new EventsAll.VolleyCallback() {
            @Override
            public void onSuccess(final List<List<String>> result,final List<Boolean> reg,final List<Boolean> fav) {

                        login l=new login(AllEvents.this);

                        l.Login(new login.AsyncCallback() {
                            @Override
                            public void onSuccess(JSONObject jsonObject) throws JSONException {

                                boolean Loggedin=jsonObject.getBoolean("loggedIn");

                                layoutManager=new LinearLayoutManager(AllEvents.this);
                                rv.setLayoutManager(layoutManager);
                                adapter=new RecyclerAllEvents(Loggedin,AllEvents.this,result.get(0),result.get(1),result.get(2),result.get(3),result.get(4),result.get(5),result.get(6),reg,fav);
                                rv.setAdapter(adapter);




                                timer.scheduleAtFixedRate(new TimerTask(){
                                    @Override
                                    public void run(){


                                        AllEvents.this.runOnUiThread(new Runnable() {
                                            public void run() {

                                                if (i<result.get(0).size()) {


                                                    Picasso.with(getApplicationContext()).load(result.get(0).get(i)).fit().into(imageView);

                                                    ScaleAnimation scaleAnimation=new ScaleAnimation(0,1,0,1);

                                                    scaleAnimation.setDuration(400);

                                                    scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

                                                    imageView.startAnimation(scaleAnimation);

                                                    i++;

                                                }
                                                else if (i==result.get(0).size()){
                                                    i=0;
                                                }


                                            }
                                        });
                                    }
                                },0,3000);


                            }
                        });

            }
        });

    }
}
