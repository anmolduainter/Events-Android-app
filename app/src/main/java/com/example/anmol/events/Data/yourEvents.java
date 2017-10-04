package com.example.anmol.events.Data;

import android.content.Context;
import android.telecom.Call;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class yourEvents {

    Context context;

    private AsyncHttpClient asyncHttpClient;
    private RequestParams requestParams;

    private static final String URL="http://192.168.0.105:3000/Events/YourEvents";

    public yourEvents(Context context){
        this.context=context;
        asyncHttpClient=new AsyncHttpClient();
        asyncHttpClient.setEnableRedirects(false);
        requestParams=new RequestParams();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(context);
        asyncHttpClient.setCookieStore(myCookieStore);

    }

    public void EventsYours(final Callback callback){

        asyncHttpClient.get(context, URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {
                    JSONObject jsonObject=new JSONObject(new String(responseBody));

                    System.out.println(jsonObject);

                    callback.success(jsonObject);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public interface Callback{
        void success(JSONObject jsonObject) throws JSONException;
    }

}
