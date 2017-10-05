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
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.*;

import cz.msebera.android.httpclient.Header;


public class EventsAll {


    AsyncHttpClient asyncHttpClient;
    RequestParams requestParams;


    RequestQueue rq;
    Context ctx;

    /* Data :-
          1. Event Image Url
          2. Event name
          3. Event date
          4. Event time
          4. Event desc
          5. Event phone
          6. Event username
     */

    List<String> imgUrl,name,date,time,desc,phone,username;
    List<Boolean>reg,fav;

    //Which Type of Event user wants to see  : - AllEvents or Registered Events
    String event;
    HashMap<String,String> hash=new HashMap<String,String>();

    List<List<String>> Actualli;

    public EventsAll(Context ctx, String event){
        this.event=event;
        this.ctx=ctx;
        hash.put("AllEvents","http://192.168.0.105:3000/android/Events/AllEvents");
        hash.put("RegisteredEvents","http://192.168.0.105:3000/Events/RegisteredEvents/a");

        asyncHttpClient=new AsyncHttpClient();
        asyncHttpClient.setEnableRedirects(true);
        requestParams=new RequestParams();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(ctx);
        asyncHttpClient.setCookieStore(myCookieStore);


    }


    //Getting All Events
    public void getAll(final VolleyCallback callback){

        //Getting Url for the event which the user wants to see.
        String URL = null;

        for(Map.Entry m:hash.entrySet()){

            if (m.getKey().equals(event)){
                URL= (String) m.getValue();
            }

        }
        asyncHttpClient.get(URL, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                JSONObject response= null;
                try {
                    response = new JSONObject(new String(responseBody));
                    System.out.println("Resp : " + response);


                    //Initializing All ArrayList
                    imgUrl=new ArrayList<>();
                    name=new ArrayList<>();
                    date=new ArrayList<>();
                    time=new ArrayList<>();
                    desc=new ArrayList<>();
                    phone=new ArrayList<>();
                    username=new ArrayList<>();
                    Actualli=new ArrayList<>();
                    reg=new ArrayList<>();
                    fav=new ArrayList<>();

                    try {

                        JSONArray jsonArray=response.getJSONArray("Result");

                        for (int i=0;i<jsonArray.length();i++){

                            JSONObject object=jsonArray.getJSONObject(i);


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

                        JSONArray jsonArray2=response.getJSONArray("Fav");
                        JSONArray jsonArray3=response.getJSONArray("Reg");
                        for (int i=0;i<jsonArray2.length();i++){

                            fav.add(jsonArray2.getBoolean(i));
                            reg.add(jsonArray3.getBoolean(i));

                        }

                        Actualli.add(imgUrl);
                        Actualli.add(name);
                        Actualli.add(date);
                        Actualli.add(time);
                        Actualli.add(desc);


                        Actualli.add(phone);
                        Actualli.add(username);

                        callback.onSuccess(Actualli,reg,fav);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("Message :  ",name.get(0));


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                String a=new String(responseBody);

                System.out.println(a);

            }
        });



    }

    public void getAllRegisteredEvents(final VolleyCallback callback){


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

                            Actualli.add(imgUrl);
                            Actualli.add(name);
                            Actualli.add(date);
                            Actualli.add(time);
                            Actualli.add(desc);

                            callback.onSuccess(Actualli,reg,fav);


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
        void onSuccess(List<List<String>> result,List<Boolean> reg,List<Boolean> fav);
    }

}
