package com.example.anmol.events;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
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
import android.view.Menu;
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
import com.example.anmol.events.Events.RegisteredEvents;
import com.example.anmol.events.Events.TodayEvents;
import com.example.anmol.events.Utils.CircleTransform;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    Menu menu;
    login l;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    RelativeLayout relativeLayout,relativeLayoutMain;
    RecyclerView UpcomingEvents; // Upcoming Events
    RecyclerView.LayoutManager layoutManagerUpcomingEvents;
    RecyclerView.Adapter adapterUpcomingEvents;

    TextView tx;


    String url="http://blog.soundidea.co.za/home/22/files/A%20quick%20guide%20to%20opt%20pic%202.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Setting ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        //Checking Login Status
        l=new login(MainActivity.this);

        //Navigation Bar Layout
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,R.string.open_drawer,R.string.close_drawer);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // Navigation View Header Part
        menu=navigationView.getMenu();
        View navHeader = navigationView.getHeaderView(0);

        //Navigation Header TextView
        tx= (TextView) navHeader.findViewById(R.id.Login_text);

        //Navigation Header Background Image
        ImageView imageViewNav=navHeader.findViewById(R.id.imageViewNav);

        //Navigation Header Profile Image
        ImageView profileImage=navHeader.findViewById(R.id.ProfileImageNav);
        Picasso.with(MainActivity.this).load("https://api.androidhive.info/images/nav-menu-header-bg.jpg").fit().into(imageViewNav);
        Glide.with(MainActivity.this).load(url).thumbnail(0.5f).bitmapTransform(new CircleTransform(this)).into(profileImage);

        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        final int height=displayMetrics.heightPixels;

        final EventsAll allData=new EventsAll(MainActivity.this,"AllEvents");

        final EventsToday allTodayEvents=new EventsToday(MainActivity.this);

        l.Login(new login.AsyncCallback() {
            @Override
            public void onSuccess(JSONObject jsonObject) throws JSONException {

                final boolean LoggedIn=jsonObject.getBoolean("loggedIn");

                MenuItem login=menu.findItem(R.id.LoginMenu);

                if (LoggedIn) {
                    login.setTitle("LogOut");
                    tx.setText("Welcome "+jsonObject.getJSONObject("user").getString("username"));
                }
                else{
                    login.setTitle("LogIn");
                    tx.setText("Please Login");
                }
//                recyclerView= (RecyclerView) findViewById(R.id.Recyclerview);
//                layoutManager=new LinearLayoutManager(MainActivity.this);
//                recyclerView.setLayoutManager(layoutManager);
//                adapter=new RecyclerNavAdapter(height,MainActivity.this,LoggedIn);
//                recyclerView.setAdapter(adapter);

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.UpcomingEventsMenu) {
            Intent i=new Intent(MainActivity.this,AllEvents.class);
            startActivity(i);

        } else if (id == R.id.BookMarkEventsMenu) {

        }

        // Today Events Menu
        else if(id==R.id.TodayEventsMenu){

            Intent i=new Intent(MainActivity.this,TodayEvents.class);

        }

        else if (id == R.id.RegisteredEventsMenu) {

            Intent i=new Intent(MainActivity.this, RegisteredEvents.class);
            startActivity(i);

        } else if (id == R.id.LoginMenu) {

            l.Login(new login.AsyncCallback() {
                @Override
                public void onSuccess(JSONObject jsonObject) throws JSONException {

                    final boolean LoggedIn=jsonObject.getBoolean("loggedIn");

                    if (LoggedIn){


                    }

                    else{

                        Intent i=new Intent(MainActivity.this,Login.class);
                        startActivity(i);

                    }


                }
            });



        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
