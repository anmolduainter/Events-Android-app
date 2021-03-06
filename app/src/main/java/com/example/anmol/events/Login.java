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

import org.json.JSONException;
import org.json.JSONObject;


public class Login extends AppCompatActivity{

    EditText email,password;
    Button loginBtn,RegBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        email= (EditText) findViewById(R.id.Email_login);
        password= (EditText) findViewById(R.id.password_login);
        loginBtn= (Button) findViewById(R.id.login_btn);
        RegBtn= (Button) findViewById(R.id.Register_btn);

        login first=new login(Login.this);

        first.Login(new login.AsyncCallback() {
            @Override
            public void onSuccess(JSONObject jsonObject) throws JSONException {
                System.out.println(jsonObject);


                boolean LoggedIn=jsonObject.getBoolean("loggedIn");
                if (LoggedIn){

                    Toast.makeText(getApplicationContext(),"successfull Login",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Login.this,MainActivity.class);
                    startActivity(i);


                }
                else{

                    Toast.makeText(getApplicationContext(),"Try Again",Toast.LENGTH_LONG).show();

                }


            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login l=new login(email.getText().toString(),password.getText().toString(),Login.this);

                l.Login(new login.AsyncCallback() {
                    @Override
                    public void onSuccess(JSONObject jsonObject) throws JSONException {

                        System.out.println(jsonObject);

                        boolean LoggedIn=jsonObject.getBoolean("loggedIn");
                        if (LoggedIn){

                            Toast.makeText(getApplicationContext(),"successfull Login",Toast.LENGTH_LONG).show();
                            Intent i=new Intent(Login.this,MainActivity.class);
                            startActivity(i);
                            Login.this.finish();

                        }
                        else{

                            Toast.makeText(getApplicationContext(),"Try Again",Toast.LENGTH_LONG).show();

                        }

                    }
                });

            }
        });

        RegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Login.this,Register.class);
                startActivity(i);
            }
        });


    }
}
