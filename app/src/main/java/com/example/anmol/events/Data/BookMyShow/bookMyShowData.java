package com.example.anmol.events.Data.BookMyShow;


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

import java.util.List;

public class bookMyShowData {

    RequestQueue rq;
    Context ctx;

    List<String> imageL,titleL,dayL,dateL,tagL,buyNowL;

    List<List<String>> li;

    // URL must be changed
    private static final String URL= "http://localhost:3000/android/BookMyShow";


    // consructor
    public bookMyShowData(Context ctx){
        this.ctx=ctx;
    }


    //getting data method
    public void getData(final bookMyShowcallback CallBack){

        rq= Volley.newRequestQueue(ctx);

        final JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i=0;i<response.length();i++){
                            try {
                                JSONObject jsonObject=response.getJSONObject(i);

                                //Image
                                String image=jsonObject.getString("image");
                                imageL.add(image);

                                //Title
                                String title=jsonObject.getString("title");
                                titleL.add(title);

                                //Day
                                String day=jsonObject.getString("day");
                                dayL.add(day);

                                //date
                                String date=jsonObject.getString("date");
                                dateL.add(date);

                                //Tag
                                String tag=jsonObject.getString("tag");
                                tagL.add(tag);

                                //BuyNow
                                String buyNow=jsonObject.getString("buyNow");
                                buyNowL.add(buyNow);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        li.add(imageL);
                        li.add(titleL);
                        li.add(dateL);
                        li.add(tagL);
                        li.add(buyNowL);

                        CallBack.getData(li);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        rq.add(jsonArrayRequest);
    }

    public interface bookMyShowcallback{
        void getData(List<List<String>> li);
    }

}
