package com.example.anmol.events;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anmol.events.Data.login;

/**
 * Created by anmol on 30/7/17.
 */

public class Login extends AppCompatActivity{

    EditText email,password;
    Button loginBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        email= (EditText) findViewById(R.id.Email_login);
        password= (EditText) findViewById(R.id.password_login);
        loginBtn= (Button) findViewById(R.id.login_btn);



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login l=new login(Login.this,email.getText().toString(),password.getText().toString());

                l.Login(new login.VolleyCallBack() {
                    @Override
                    public void onSuccess(Boolean LoggedIn) {

                        if (LoggedIn){
                            Intent i=new Intent(Login.this,MainActivity.class);
                            startActivity(i);
                        }

                        else{

                            Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_LONG).show();

                        }


                    }
                });

            }
        });



    }
}
