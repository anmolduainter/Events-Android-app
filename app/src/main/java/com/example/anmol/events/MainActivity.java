package com.example.anmol.events;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.anmol.events.Adapter.RecyclerNavAdapter;
import com.example.anmol.events.Adapter.RecyclerUpComingEventsVertical;
import com.example.anmol.events.Adapter.RecyclerUpcomingEventsMain;
import com.example.anmol.events.Data.EventsAll;
import com.example.anmol.events.Data.EventsToday;
import com.example.anmol.events.Data.login;
import com.example.anmol.events.Events.AllEvents;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    RelativeLayout relativeLayout,relativeLayoutMain;
    RecyclerView UpcomingEvents; // Upcoming Events
    RecyclerView.LayoutManager layoutManagerUpcomingEvents;
    RecyclerView.Adapter adapterUpcomingEvents;

    TextView tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        login l=new login(MainActivity.this);

        relativeLayoutMain= (RelativeLayout) findViewById(R.id.RelativeLayoutMAIN);


        Glide.with(getApplicationContext()).load("http://www.iam-active.com/wp-content/uploads/2017/06/6.jpg").bitmapTransform(new BlurTransformation(getApplicationContext(),100)).into(new SimpleTarget<GlideDrawable>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {

                relativeLayoutMain.setBackground(resource);

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(MainActivity.this,Login.class);
                startActivity(i);

            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,R.string.open_drawer,R.string.close_drawer);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        final int height=displayMetrics.heightPixels;

        relativeLayout= (RelativeLayout) findViewById(R.id.RelativeLayoutnav);

        tx= (TextView) findViewById(R.id.Login_text);

        Glide.with(getApplicationContext()).load("https://www.w3schools.com/css/trolltunga.jpg").bitmapTransform(new BlurTransformation(getApplicationContext(),100)).into(new SimpleTarget<GlideDrawable>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {

                relativeLayout.setBackground(resource);

            }
        });

        final EventsAll allData=new EventsAll(MainActivity.this,"AllEvents");

        final EventsToday allTodayEvents=new EventsToday(MainActivity.this);

        l.Login(new login.AsyncCallback() {
            @Override
            public void onSuccess(JSONObject jsonObject) throws JSONException {

                final boolean LoggedIn=jsonObject.getBoolean("loggedIn");


                if (LoggedIn) {
                    tx.setText("Welcome "+jsonObject.getJSONObject("user").getString("username"));
                }
                else{
                    tx.setText("Please Login");
                }
                recyclerView= (RecyclerView) findViewById(R.id.Recyclerview);
                layoutManager=new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                adapter=new RecyclerNavAdapter(height,MainActivity.this,LoggedIn);
                recyclerView.setAdapter(adapter);

                final List<List<List<String>>> ResultingList;

                ResultingList=new ArrayList<List<List<String>>>();

               allData.getAll(new EventsAll.VolleyCallback() {
                   @Override
                   public void onSuccess(final List<List<String>> result) {

                       allTodayEvents.getData(new EventsToday.AsyncCallback() {
                           @Override
                           public void onSuccess(List<List<String>> list, boolean Today, boolean LoggedIn) {

                               ResultingList.add(result);

                               ResultingList.add(list);

                               UpcomingEvents= (RecyclerView) findViewById(R.id.UpcomingEventsMainScreen);
                               layoutManagerUpcomingEvents=new LinearLayoutManager(MainActivity.this);
                               UpcomingEvents.setLayoutManager(layoutManagerUpcomingEvents);
                               adapterUpcomingEvents=new RecyclerUpComingEventsVertical(LoggedIn,Today,MainActivity.this,ResultingList);
                               UpcomingEvents.setAdapter(adapterUpcomingEvents);



                           }
                       });
                   }
               });


            }
        });

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


