package com.example.anmol.events.Events;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.anmol.events.R;

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

        toolbar= (Toolbar) findViewById(R.id.collapsinggToolbarregEve);
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

    }
}
