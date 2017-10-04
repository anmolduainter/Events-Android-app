package com.example.anmol.events.Data;

import android.app.DownloadManager;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class logout {

    String email, password;
    Context ctx;

    String TAG = "MOVIE";

    final static String URL = "http://192.168.0.105:3000/android/logOut";

    AsyncHttpClient asyncHttpClient;
    RequestParams requestParams;

    public logout(Context ctx) {
        asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.setEnableRedirects(true);
        requestParams = new RequestParams();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(ctx);
        asyncHttpClient.setCookieStore(myCookieStore);
        this.ctx=ctx;
    }


    public void LogoutSuccess() {

        asyncHttpClient.get(URL, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {
                    JSONObject testV = new JSONObject(new String(responseBody));

                    if (testV.getBoolean("status")){

                        Toast.makeText(ctx,"LogOut Success",Toast.LENGTH_LONG).show();

                    }

                    System.out.println(testV);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {


                String a = new String(responseBody);

                System.out.println(a);

            }
        });


    }
}

