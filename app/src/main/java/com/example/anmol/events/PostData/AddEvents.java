package com.example.anmol.events.PostData;


import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class AddEvents {

    Context ctx;
    String image;
    String name;
    String date;
    String time;
    String desc;

    AsyncHttpClient asyncHttpClient;
    RequestParams requestParams;

    private static final String URL="http://192.168.0.106:3000/AddEvents/AddEvent";

    public AddEvents(Context ctx,String image,String name,String date,String time,String desc) {

        this.ctx = ctx;
        this.image = image;
        this.name = name;
        this.date = date;
        this.time = time;
        this.desc = desc;

        asyncHttpClient=new AsyncHttpClient();
        requestParams=new RequestParams();
        asyncHttpClient.setEnableRedirects(true);
        PersistentCookieStore persistentCookieStore=new PersistentCookieStore(ctx);
        asyncHttpClient.setCookieStore(persistentCookieStore);

    }

    public void postEvent(final AsyncCallBackAdd asyncCallback){

        requestParams.put("imageUrl",image);
        requestParams.put("title",name);
        requestParams.put("date",date);
        requestParams.put("time",time);
        requestParams.put("desc",desc);

        asyncHttpClient.post(URL, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {
                    JSONObject jsonObject=new JSONObject(new String(responseBody));
                    asyncCallback.onSuccess(jsonObject);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    public interface AsyncCallBackAdd{
        void onSuccess(JSONObject jsonObject) throws JSONException;
    }
}
