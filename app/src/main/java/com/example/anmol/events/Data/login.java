package com.example.anmol.events.Data;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by anmol on 30/7/17.
 */

public class login {

    String email,password;
    Context ctx;
    RequestQueue rq;
    final static String URL="http://192.168.0.105:3000/login/and";

    public login(Context ctx,String email, String password){

        this.email=email;
        this.password=password;
        this.ctx=ctx;

    }

    public void Login(final VolleyCallBack callBack){

        rq= Volley.newRequestQueue(ctx);


        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println(response);
                        try {
                            JSONObject jsonObject=new JSONObject(response.toString());
                            System.out.println(jsonObject.getBoolean("LoggedIn"));

                            callBack.onSuccess(jsonObject.getBoolean("LoggedIn"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        )

        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();
                params.put("username",email);
                params.put("password",password);
                return params;
            }
        };

      rq.add(stringRequest);

    }


    public interface VolleyCallBack{
        void onSuccess(Boolean LoggedIn);
    }



}
