package com.example.anmol.events.Data;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class EventsToday {

    Context ctx;

    AsyncHttpClient asyncHttpClient;
    RequestParams requestParams;

    List<List<String>> Actualli;
    List<String> imgUrl,name,date,time,desc,phone,username;

    List<String> timeArr;

    public static final String URL="http://192.168.0.106:3000/android/Events/TodayEvents";


    public EventsToday(Context ctx){

        this.ctx=ctx;
        asyncHttpClient=new AsyncHttpClient();
        asyncHttpClient.setEnableRedirects(true);
        requestParams=new RequestParams();

    }



    public void getData(final AsyncCallback asyncCallback){

        asyncHttpClient.get(URL, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {

                    imgUrl=new ArrayList<>();
                    name=new ArrayList<>();
                    date=new ArrayList<>();
                    time=new ArrayList<>();
                    desc=new ArrayList<>();
                    phone=new ArrayList<String>();
                    username=new ArrayList<String>();
                    timeArr=new ArrayList<String>();

                    Actualli=new ArrayList<List<String>>();


                    JSONObject testV=new JSONObject(new String(responseBody));


                    boolean Today=testV.getBoolean("Today");

                    boolean LoggedIn = testV.getBoolean("LoggedIn");

                    if (Today) {


                        JSONArray jsonArray = testV.getJSONArray("Result");

                        JSONArray jsonArray1 = testV.getJSONArray("Arr");

                        JSONArray jsonArray2 = testV.getJSONArray("TimeArr");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject object = jsonArray.getJSONObject(i);

                            JSONObject object1 = jsonArray1.getJSONObject(i);

                            //  System.out.println(object);

                            imgUrl.add(object.getString("imgUrl"));
                            name.add(object.getString("name"));
                            date.add(object.getString("date"));
                            time.add(object.getString("time"));
                            desc.add(object.getString("desc"));
                            phone.add(String.valueOf(object1.getInt("phone")));
                            username.add(object1.getString("username"));

                            timeArr.add(jsonArray2.get(i).toString());

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
                        Actualli.add(phone);
                        Actualli.add(username);
                        Actualli.add(timeArr);

                        System.out.println(testV);
                    }
                    else{

                    }


                    System.out.println("Actual Li : "+Actualli);

                    asyncCallback.onSuccess(Actualli,Today,LoggedIn);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                String a=new String(responseBody);

                System.out.println(a);

            }
        });


    }


    public interface AsyncCallback{
        void onSuccess(List<List<String>> list,boolean Today,boolean LoggedIn);
    }





}
