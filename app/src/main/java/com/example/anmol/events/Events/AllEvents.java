package com.example.anmol.events.Events;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.anmol.events.Adapter.RecyclerAllEvents;
import com.example.anmol.events.Data.EventsAll;
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

    String URL="http://192.168.0.105:3000/Events/JSON";
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

        rv= (RecyclerView) findViewById(R.id.recyclerAllEvents);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {


            int scroll=-1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (scroll==-1){


                    scroll=appBarLayout.getTotalScrollRange();

                }

                if (verticalOffset * -1 >= scroll-150 ){
                    collapsingToolbarLayout.setTitle("EventsAll");
                    collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

                }

                else{

                    collapsingToolbarLayout.setTitle(" ");

                }


            }
        });


        EventsAll allData=new EventsAll(AllEvents.this,"AllEvents");

        allData.getAll(new EventsAll.VolleyCallback() {
            @Override
            public void onSuccess(List<List<String>> result) {


                        layoutManager=new LinearLayoutManager(AllEvents.this);
                        rv.setLayoutManager(layoutManager);
                        adapter=new RecyclerAllEvents(AllEvents.this,result.get(0),result.get(1),result.get(2),result.get(3),result.get(4),result.get(5),result.get(6));
                        rv.setAdapter(adapter);


            }
        });


//        rq=Volley.newRequestQueue(EventsAll.this);
//
//       // final ProgressDialog progressDialog = ProgressDialog.show(getApplicationContext(), "", "Pro Start", false, false);
//        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,URL, null,
//
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//
//                        imgUrl=new ArrayList<>();
//                        name=new ArrayList<>();
//                        date=new ArrayList<>();
//                        time=new ArrayList<>();
//                        desc=new ArrayList<>();
//                        phone=new ArrayList<>();
//                        username=new ArrayList<>();
//
//                        try {
//         //                   progressDialog.cancel();
//                            JSONArray jsonArray=response.getJSONArray("Result");
//
//                            for (int i=0;i<jsonArray.length();i++){
//
//                                JSONObject object=jsonArray.getJSONObject(i);
//
//                              //  System.out.println(object);
//
//                                imgUrl.add(object.getString("imgUrl"));
//                                name.add(object.getString("name"));
//                                date.add(object.getString("date"));
//                                time.add(object.getString("time"));
//                                desc.add(object.getString("desc"));
////                                phone.add(object.getString("phone"));
////                                username.add(object.getString("username"));
//
//                                System.out.println(imgUrl.get(i));
//                                System.out.println(name.get(i));
//                                System.out.println(date.get(i));
//                                System.out.println(time.get(i));
//
//                            }
//
//                            JSONArray jsonArray1=response.getJSONArray("Arr");
//                            for (int i=0;i<jsonArray1.length();i++){
//
//                                JSONObject object=jsonArray1.getJSONObject(i);
//                                phone.add(object.getString("phone"));
//                                username.add(object.getString("username"));
//
//                            }
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                        Log.i("Message :  ",name.get(0));
//
//
//                        layoutManager=new LinearLayoutManager(EventsAll.this);
//                        rv.setLayoutManager(layoutManager);
//                        adapter=new RecyclerAllEvents(EventsAll.this,imgUrl,name,date,time,desc,phone,username);
//                        rv.setAdapter(adapter);
//
//                    }
//
//
//
//                },
//
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//
//
//                    }
//                }
//
//        );
//
//        rq.add(jsonObjectRequest);

    }
}
