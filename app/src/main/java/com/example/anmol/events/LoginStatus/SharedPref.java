package com.example.anmol.events.LoginStatus;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.anmol.events.R;

/**
 * Created by anmol on 31/7/17.
 */

public class SharedPref {

    Context ctx;
    boolean LoggedIn;

    public SharedPref(Context ctx,boolean LoggedIn){
        this.ctx=ctx;
        this.LoggedIn=LoggedIn;
    }

    public void setStatus(){
        SharedPreferences sharedPref = ctx.getSharedPreferences(String.valueOf((R.string.Login_status_File)), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(String.valueOf(R.string.LOGIN_STATUS),LoggedIn);
        editor.commit();
    }

    public boolean getStatus(){

        SharedPreferences sharedPref = ctx.getSharedPreferences(String.valueOf((R.string.Login_status_File)), Context.MODE_PRIVATE);
        boolean status = sharedPref.getBoolean(String.valueOf(R.string.LOGIN_STATUS),false);
        return status;

    }


}
