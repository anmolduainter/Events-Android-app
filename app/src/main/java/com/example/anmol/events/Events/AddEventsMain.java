package com.example.anmol.events.Events;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anmol.events.MainActivity;
import com.example.anmol.events.PostData.AddEvents;
import com.example.anmol.events.R;

import org.json.JSONException;
import org.json.JSONObject;

public class AddEventsMain extends AppCompatActivity{

    private EditText imageURLtx;
    private EditText titletx;
    private EditText desctx;
    private EditText datetx;
    private EditText timetx;
    private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addevents_main);

        // initialize views
        initViews();

        // Clicking submit clicked
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitClicked();
            }
        });

    }

    public void initViews(){
        imageURLtx= (EditText) findViewById(R.id.imageUrlEditText);
        titletx= (EditText) findViewById(R.id.titleEditText);
        desctx= (EditText) findViewById(R.id.descEditText);
        datetx= (EditText) findViewById(R.id.dateEditText);
        timetx= (EditText) findViewById(R.id.timeEditText);
        btn = (Button) findViewById(R.id.SubmitAddEvents);
    }

    //Submitting clicked
    public void submitClicked(){

        String url=imageURLtx.getText().toString();
        String title=titletx.getText().toString();
        String desc=desctx.getText().toString();
        String date=datetx.getText().toString();
        String time=timetx.getText().toString();

        if (url.equals("") || title .equals("") || desc.equals("") || date.equals("") || time.equals("")){

            Toast.makeText(getApplicationContext(),"Please Fill All",Toast.LENGTH_LONG).show();
        }

        AddEvents addEvents=new AddEvents(AddEventsMain.this,url,title,date,time,desc);
        addEvents.postEvent(new AddEvents.AsyncCallBackAdd() {
            @Override
            public void onSuccess(JSONObject jsonObject) throws JSONException {

                System.out.println("AddEvents : " + jsonObject);

                boolean st=jsonObject.getBoolean("success");

                // Response - true
                if (st){
                    Intent i=new Intent(AddEventsMain.this, MainActivity.class);
                    startActivity(i);
                    AddEventsMain.this.finish();
                }
            }
        });

    }

}
