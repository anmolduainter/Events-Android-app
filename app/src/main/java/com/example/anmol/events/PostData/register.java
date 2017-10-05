package com.example.anmol.events.PostData;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class register {


    Context ctx;
    String username;
    String email;
    String phone;
    String pass;

    private AsyncHttpClient asyncHttpClient;
    private RequestParams requestParams;

    private static final String URL="http://192.168.0.105:3000/android/Register";

    public register(Context ctx, String username, String email, String phone ,String pass){
        this.ctx=ctx;
        this.username=username;
        this.email=email;
        this.phone=phone;
        this.pass=pass;

        asyncHttpClient=new AsyncHttpClient();
        requestParams=new RequestParams();
        asyncHttpClient.setEnableRedirects(true);
        PersistentCookieStore persistentCookieStore=new PersistentCookieStore(ctx);
        asyncHttpClient.setCookieStore(persistentCookieStore);

    }


    public void sendData(){

        asyncHttpClient.post(URL, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {

                    System.out.println(new String(responseBody));
                    JSONObject jsonObject=new JSONObject(new String(responseBody));

                    //Checking the output
                    System.out.println("AddEvents : " + jsonObject);

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }



}
