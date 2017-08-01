package com.example.anmol.events.Data;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.anmol.events.Adapter.RecyclerAllEvents;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.*;

/**
 * Created by anmol on 30/7/17.
 */

public class EventsAll {


    RequestQueue rq;
    Context ctx;
    List<String> imgUrl,name,date,time,desc,phone,username;
    String event;
    HashMap<String,String> hash=new HashMap<String,String>();

    List<List<String>> Actualli;

    public EventsAll(Context ctx, String event){
        this.event=event;
        this.ctx=ctx;
        hash.put("AllEvents","http://192.168.0.106:3000/Events/JSON");

    }

    public void getAll(final VolleyCallback callback){

        String URL = null;

        for(Map.Entry m:hash.entrySet()){

            if (m.getKey().equals(event)){
                URL= (String) m.getValue();
            }

        }
        rq= Volley.newRequestQueue(ctx);
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
                        Actualli=new ArrayList<>();

                        try {
                            //                   progressDialog.cancel();
                            JSONArray jsonArray=response.getJSONArray("Result");

                            for (int i=0;i<jsonArray.length();i++){

                                JSONObject object=jsonArray.getJSONObject(i);

                                //  System.out.println(object);

                                imgUrl.add(object.getString("imgUrl"));
                                name.add(object.getString("name"));
                                date.add(object.getString("date"));
                                time.add(object.getString("time"));
                                desc.add(object.getString("desc"));
//                                phone.add(object.getString("phone"));
//                                username.add(object.getString("username"));

                                System.out.println(imgUrl.get(i));
                                System.out.println(name.get(i));
                                System.out.println(date.get(i));
                                System.out.println(time.get(i));



                            }

                            JSONArray jsonArray1=response.getJSONArray("Arr");
                            for (int i=0;i<jsonArray1.length();i++){

                                JSONObject object=jsonArray1.getJSONObject(i);
                                phone.add(object.getString("phone"));
                                username.add(object.getString("username"));

                            }

                            Actualli.add(imgUrl);
                            Actualli.add(name);
                            Actualli.add(date);
                            Actualli.add(time);
                            Actualli.add(desc);


                            Actualli.add(phone);
                            Actualli.add(username);




                            callback.onSuccess(Actualli);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.i("Message :  ",name.get(0));

                    }



                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {



                    }
                }

        );

        rq.add(jsonObjectRequest);

    }


    public interface VolleyCallback{
        void onSuccess(List<List<String>> result);
    }

}
