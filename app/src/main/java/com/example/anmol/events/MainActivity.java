package com.example.anmol.events;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.anmol.events.Adapter.RecyclerUpComingEventsVertical;
import com.example.anmol.events.Data.EventsAll;
import com.example.anmol.events.Data.EventsToday;
import com.example.anmol.events.Data.login;
import com.example.anmol.events.Data.logout;
import com.example.anmol.events.Events.AddEventsMain;
import com.example.anmol.events.Events.AllEvents;
import com.example.anmol.events.Events.RegisteredEvents;
import com.example.anmol.events.Events.TodayEvents;
import com.example.anmol.events.Events.YourEvents;
import com.example.anmol.events.Utils.CircleTransform;
import com.glide.slider.library.Animations.DescriptionAnimation;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderTypes.BaseSliderView;
import com.glide.slider.library.SliderTypes.TextSliderView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

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

    private SliderLayout sliderLayout;

    // Url of images
    String URL[]={

            "https://pbs.twimg.com/profile_images/663984159556268032/fWFLE3QM.jpg",
            "https://eventimages.insider.in/insiderLogo.png",
            "https://lh3.googleusercontent.com/kSOoODI0T2kS0awZ-4deECTIZDrlzR6l2Ua6yKymWhyujxjSPZPEAa91bPj56Qka5Ao=w300"
    };


    String url="http://blog.soundidea.co.za/home/22/files/A%20quick%20guide%20to%20opt%20pic%202.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Setting ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //Slider
        sliderLayout= (SliderLayout) findViewById(R.id.slider);
        SlidingLayout();

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
        Picasso.with(MainActivity.this).load("http://www.ucb.ac.uk/content/images/courses/hospitality-tourism-events/events-management-3.jpg").fit().into(imageViewNav);
        Glide.with(MainActivity.this).load(url).thumbnail(0.5f).bitmapTransform(new CircleTransform(this)).into(profileImage);

        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        final int height=displayMetrics.heightPixels;

        l.Login(new login.AsyncCallback() {
            @Override
            public void onSuccess(JSONObject jsonObject) throws JSONException {

                final boolean LoggedIn=jsonObject.getBoolean("loggedIn");

                MenuItem login=menu.findItem(R.id.LoginMenu);

                if (LoggedIn) {
                    login.setTitle("LogOut");
                    tx.setText("Welcome "+jsonObject.getJSONObject("user").getJSONObject("user").getString("username"));
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


                               UpcomingEvents= (RecyclerView) findViewById(R.id.UpcomingEventsMainScreen);
                               layoutManagerUpcomingEvents=new LinearLayoutManager(MainActivity.this);
                               UpcomingEvents.setLayoutManager(layoutManagerUpcomingEvents);
                               adapterUpcomingEvents=new RecyclerUpComingEventsVertical(MainActivity.this);
                               UpcomingEvents.setAdapter(adapterUpcomingEvents);

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


        // Clicking Add Event on main activity
        if (id == R.id.action_settings) {

            l.Login(new login.AsyncCallback() {
                @Override
                public void onSuccess(JSONObject jsonObject) throws JSONException {
                    final boolean LoggedIn=jsonObject.getBoolean("loggedIn");
                     if (LoggedIn){
                         Intent i=new Intent(MainActivity.this, AddEventsMain.class);
                         startActivity(i);
                     }
                     else{
                         Toast.makeText(MainActivity.this, "Login First", Toast.LENGTH_SHORT).show();
                         Intent i=new Intent(MainActivity.this,Login.class);
                         startActivity(i);
                     }
                }
            });

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(final MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.UpcomingEventsMenu) {
            Intent i=new Intent(MainActivity.this,AllEvents.class);
            startActivity(i);

        }

        // Today Events Menu
        else if(id==R.id.TodayEventsMenu){

            Intent i=new Intent(MainActivity.this,TodayEvents.class);
            startActivity(i);

        }

        //Your Events
        else if (id==R.id.YourEventsMenu){

            l.Login(new login.AsyncCallback() {
                @Override
                public void onSuccess(JSONObject jsonObject) throws JSONException {

                    final boolean LoggedIn=jsonObject.getBoolean("loggedIn");

                    // Logged In True
                    if (LoggedIn){
                        Intent i=new Intent(MainActivity.this, YourEvents.class);
                        startActivity(i);
                    }
                    else{
                        Intent i=new Intent(MainActivity.this,Login.class);
                        startActivity(i);
                    }

                }
            });

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
                        new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Sure Want To LogOut ? ")
                                .setConfirmText("Yes")
                                .setCancelText("No")
                                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismissWithAnimation();
                                    }
                                })
                                // Confirm
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        tx.setText("Please Login");
                                        item.setTitle("LogIn");
                                        logout l=new logout(MainActivity.this);
                                        l.LogoutSuccess();
                                        sDialog.dismissWithAnimation();
                                    }
                                })
                                .show();

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

    private void SlidingLayout(){

        for (int i=0;i<URL.length;i++){
            TextSliderView textSliderView=new TextSliderView(MainActivity.this);
            textSliderView.image(URL[i]);
            textSliderView.setCenterCrop(false);
            sliderLayout.addSlider(textSliderView);
        }

        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setDuration(4000);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());

    }

}
