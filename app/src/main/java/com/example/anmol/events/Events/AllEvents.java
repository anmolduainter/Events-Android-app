package com.example.anmol.events.Events;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.anmol.events.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anmol on 27/7/17.
 */

public class AllEvents extends AppCompatActivity {

    String URL="http://localhost:3000/Events";
    RequestQueue rq;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    RecyclerView rv;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    List<String> imgUrl,name,date,time,desc,phone,username;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allevents);

        toolbar= (Toolbar) findViewById(R.id.tooolbarallEve);
        collapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsinggToolbarallEve);
        appBarLayout= (AppBarLayout) findViewById(R.id.appBaralleve);

//        rv= (RecyclerView) findViewById(R.id.recyclerAllEvents);
//        layoutManager=new LinearLayoutManager(AllEvents.this);
////        adapter=new


        rq=Volley.newRequestQueue(AllEvents.this);

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,URL, null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        imgUrl=new ArrayList<>();
                        name=new ArrayList<>();
                        date=new ArrayList<>();
                        time=new ArrayList<>();
                        desc=new ArrayList<>();
                        phone=new ArrayList<>();
                        username=new ArrayList<>();

                        try {
                            JSONArray jsonArray=response.getJSONArray("Result");

                            for (int i=0;i<jsonArray.length();i++){

                                JSONObject object=jsonArray.getJSONObject(i);

                                imgUrl.add(object.getString("imgUrl"));
                                name.add(object.getString("name"));
                                date.add(object.getString("date"));
                                time.add(object.getString("time"));
                                desc.add(object.getString("desc"));
                                phone.add(object.getString("phone"));
                                username.add(object.getString("username"));


                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        );



    }
}
