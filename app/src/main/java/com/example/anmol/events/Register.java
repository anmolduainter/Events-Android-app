package com.example.anmol.events;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anmol.events.PostData.register;

public class Register extends AppCompatActivity {

    EditText email,username,pass,confirm,phone;
    Button sub;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register);

        // initialize views
        initViews();

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 subClicked();
            }
        });

    }

    // To initialize views
    public void initViews(){
        email= (EditText) findViewById(R.id.Email_REG);
        username= (EditText) findViewById(R.id.Username_REG);
        pass= (EditText) findViewById(R.id.Pass_REG);
        confirm= (EditText) findViewById(R.id.ConfirmPass_REG);
        phone= (EditText) findViewById(R.id.Phone_REG);
        sub= (Button) findViewById(R.id.Submit_REG);
    }

    //subClicked
    public void subClicked(){

        String emailText=email.getText().toString();
        String user=username.getText().toString();
        String password=pass.getText().toString();
        String confirmPass=confirm.getText().toString();
        String phoneText=phone.getText().toString();

        if (emailText.equals("") || user.equals("")  || password.equals("") || confirmPass.equals("") ||phoneText.equals("")){
            Toast.makeText(Register.this, "One or more fields are empty", Toast.LENGTH_SHORT).show();
        }
        else{
            if (password.equals(confirmPass)){
                register r=new register(Register.this,user,emailText,phoneText,password);
                r.sendData();
            }
            else{

                Toast.makeText(Register.this,"Please Confirm Your Password",Toast.LENGTH_LONG).show();

            }
        }
    }
}
