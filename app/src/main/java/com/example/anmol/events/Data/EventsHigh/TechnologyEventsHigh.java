package com.example.anmol.events.Data.EventsHigh;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by anmol on 15/8/17.
 */

public class TechnologyEventsHigh {

    final static String URL="http://192.168.0.106:3000/android/EventsHigh/Technology";

    AsyncHttpClient asyncHttpClient;
    RequestParams requestParams;

    List<List<String>> Actualli;
    List<String> imgUrl,href,title,datetime,evenue,genre;

    public TechnologyEventsHigh(Context ctx){
        asyncHttpClient=new AsyncHttpClient();
        asyncHttpClient.setEnableRedirects(true);
        requestParams=new RequestParams();
    }


    public void getData(final TechnologyEventsHigh.AsyncCallback asyncCallback){

        asyncHttpClient.get(URL, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {

                    imgUrl=new ArrayList<>();
                    href=new ArrayList<>();
                    title=new ArrayList<>();
                    datetime=new ArrayList<>();
                    evenue=new ArrayList<>();

                    genre=new ArrayList<String>();

                    Actualli=new ArrayList<List<String>>();

                    JSONArray jsonArray=new JSONArray(new String(responseBody));

                    System.out.println(jsonArray);

                    for (int i=0;i<jsonArray.length();i++){

                        imgUrl.add(jsonArray.getJSONObject(i).getString("image"));


                        System.out.println(i +" Value " + jsonArray.getJSONObject(i).getString("href"));


                        href.add(jsonArray.getJSONObject(i).getString("href"));


                        title.add(jsonArray.getJSONObject(i).getString("title"));

                        datetime.add(jsonArray.getJSONObject(i).getString("dateTime"));
                        evenue.add(jsonArray.getJSONObject(i).getString("evenue"));

                        genre.add(jsonArray.getJSONObject(i).getString("genre"));

                    }

                    Actualli.add(imgUrl);
                    Actualli.add(href);
                    Actualli.add(title);
                    Actualli.add(datetime);
                    Actualli.add(evenue);
                    Actualli.add(genre);

                    asyncCallback.onSuccess(Actualli);


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
        void onSuccess(List<List<String>> list);
    }




}
