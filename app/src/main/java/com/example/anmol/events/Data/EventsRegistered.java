package com.example.anmol.events.Data;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by anmol on 1/8/17.
 */

public class EventsRegistered {


    final static String URL="http://192.168.0.106:3000/android/Events/RegisteredEvents";

    AsyncHttpClient asyncHttpClient;
    RequestParams requestParams;

    List<List<String>> Actualli;
    List<String> imgUrl,name,date,time,desc;

    List<Boolean> timeArr;

    public EventsRegistered(Context ctx){
        asyncHttpClient=new AsyncHttpClient();
        asyncHttpClient.setEnableRedirects(true);
        requestParams=new RequestParams();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(ctx);
        asyncHttpClient.setCookieStore(myCookieStore);

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

                    Actualli=new ArrayList<List<String>>();


                    JSONObject testV=new JSONObject(new String(responseBody));


                    JSONArray jsonArray=testV.getJSONArray("ResultArr");

                    JSONArray jsonArray1=testV.getJSONArray("TimeArr");

                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject object=jsonArray.getJSONObject(i);

                        //  System.out.println(object);

                        imgUrl.add(object.getString("imgUrl"));
                        name.add(object.getString("name"));
                        date.add(object.getString("date"));
                        time.add(object.getString("time"));
                        desc.add(object.getString("desc"));
                        timeArr.add(jsonArray1.getBoolean(i));
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



                    System.out.println(testV);

                    asyncCallback.onSuccess(Actualli,timeArr);

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
        void onSuccess(List<List<String>> list,List<Boolean> list1);
    }




}
