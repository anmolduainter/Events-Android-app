package com.example.anmol.events.PostData;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by anmol on 1/8/17.
 */

public class RegisterEvents {

    String name,date,time;
    AsyncHttpClient asyncHttpClient;
    RequestParams requestParams;
    int pos;

    private static final String URL[]={"http://192.168.0.106:3000/Events/Register",
                                       "http://192.168.0.106:3000/Events/BookMark"};

    Context ctx;

    public RegisterEvents(Context ctx, String name, String date, String time,int pos){

        asyncHttpClient=new AsyncHttpClient();
        requestParams=new RequestParams();
        asyncHttpClient.setEnableRedirects(true);
        PersistentCookieStore persistentCookieStore=new PersistentCookieStore(ctx);
        asyncHttpClient.setCookieStore(persistentCookieStore);
        this.name=name;
        this.date=date;
        this.time=time;
        this.pos=pos;
        this.ctx=ctx;
    }

    public void POST(final AsyncCallBack asyncCallBack){

        requestParams.put("name",name);
        requestParams.put("date",date);
        requestParams.put("time",time);

        asyncHttpClient.post(URL[pos], requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {
                    JSONObject jsonObject=new JSONObject(new String(responseBody));

                       asyncCallBack.onSuccess(jsonObject);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {



            }
        });

    }


    public interface AsyncCallBack{
        void onSuccess(JSONObject jsonObject) throws JSONException;
    }


}
