package com.example.anmol.events.Data;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class yourEvents {

    Context ctx;
    String TAG="MOVIE";

    final static String URL="http://192.168.0.105:3000/android/Events/YourEvents";

    AsyncHttpClient asyncHttpClient;
    RequestParams requestParams;

    public yourEvents(Context ctx){
        asyncHttpClient=new AsyncHttpClient();
        asyncHttpClient.setEnableRedirects(true);
        requestParams=new RequestParams();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(ctx);
        asyncHttpClient.setCookieStore(myCookieStore);

    }


    public void EventsYours(final AsyncCallback asyncCallback){

        asyncHttpClient.get(URL, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {
                    JSONObject testV=new JSONObject(new String(responseBody));

                    System.out.println(testV);

                    asyncCallback.onSuccess(testV);

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
        void onSuccess(JSONObject jsonObject) throws JSONException;
    }


}
