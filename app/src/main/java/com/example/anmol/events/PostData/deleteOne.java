package com.example.anmol.events.PostData;


import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class deleteOne {

    private Context ctx;
    private String name;
    private String date;
    private String time;
    private int id;

    private AsyncHttpClient asyncHttpClient;
    private RequestParams requestParams;

    private static final String URL="http://192.168.0.105:3000/DeleteEvents/deleteOne";

    public deleteOne(Context ctx,int id,String name,String date,String time){
        this.ctx=ctx;
        this.id=id;
        this.name=name;
        this.date=date;
        this.time=time;
        asyncHttpClient = new AsyncHttpClient();
        requestParams=new RequestParams();
        asyncHttpClient.setEnableRedirects(true);
        PersistentCookieStore persistentCookieStore=new PersistentCookieStore(ctx);
        asyncHttpClient.setCookieStore(persistentCookieStore);
    }

    // Method to delete
    public void Delete(final CallBack callBack){

        requestParams.put("id",id);
        requestParams.put("name",name);
        requestParams.put("date",date);
        requestParams.put("time",time);

        asyncHttpClient.post(URL, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONObject jsonObject=new JSONObject(new String(responseBody));
                    System.out.println(jsonObject);

                    callBack.onSuccess(jsonObject);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public interface CallBack{
        void onSuccess(JSONObject jsonObject);
    }

}
