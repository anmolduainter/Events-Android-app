package com.example.anmol.events.Data.Insider;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InsiderData {

    //Request Que
    RequestQueue rq;
    private static final String URL="http://localhost:3000/android/Insider/delhi";

    private List<String> nameL,imgL,dateL,placeL;

    private List<List<String>> li;
    Context ctx;

    //constructor
    public InsiderData(Context ctx){
        this.ctx=ctx;
        nameL=new ArrayList<>();
        imgL=new ArrayList<>();
        dateL=new ArrayList<>();
        placeL=new ArrayList<>();
        li=new ArrayList<>();
    }

    // Getting Data
    public void getData(final InsiderCallBack insiderCallBack){

        rq= Volley.newRequestQueue(ctx);

        final JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        System.out.println(response);

                        // response - JsonArray

                        for (int i=0;i<response.length();i++){
                            try {
                                //getting Json Object
                                JSONObject jsonObject=response.getJSONObject(i);

                                //getting name
                                String name=jsonObject.getString("name");
                                nameL.add(name);

                                //getting image
                                String img=jsonObject.getString("img");
                                imgL.add(img);

                                //getting date
                                String date=jsonObject.getString("date");
                                dateL.add(date);

                                //getting place
                                String place=jsonObject.getString("place");
                                placeL.add(place);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        li.add(nameL);
                        li.add(imgL);
                        li.add(dateL);
                        li.add(placeL);

                        insiderCallBack.result(li);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        System.out.println("Getting Error");

                    }
                }
        );
        rq.add(jsonArrayRequest);
    }

    public interface InsiderCallBack{
        void result(List<List<String>> list);
    }

}
