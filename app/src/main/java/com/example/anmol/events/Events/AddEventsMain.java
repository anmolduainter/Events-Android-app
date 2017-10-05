package com.example.anmol.events.Events;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.anmol.events.MainActivity;
import com.example.anmol.events.PostData.AddEvents;
import com.example.anmol.events.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddEventsMain extends AppCompatActivity{

    private EditText imageURLtx;
    private EditText titletx;
    private EditText desctx;
    private EditText datePickerEdit,timePickerEdit,timePickerEdit1;
    private ImageView datePicker;
    private ImageView timePicker,timePicker1;
    private Button btn;

    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addevents_main);

        // initialize views
        initViews();

        //Clicking datePicker
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerClicked();
            }
        });

        //Clicking TimePicker
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerClicked();
            }
        });

        //Cliking TimePicker1
        timePicker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePicker1Clicked();
            }
        });

        // Clicking submit clicked
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitClicked();
            }
        });

    }

    // for initializing the views
    public void initViews(){
        imageURLtx= (EditText) findViewById(R.id.imageUrlEditText);
        titletx= (EditText) findViewById(R.id.titleEditText);
        desctx= (EditText) findViewById(R.id.descEditText);
        datePicker= (ImageView) findViewById(R.id.DatePicker);
        timePicker= (ImageView) findViewById(R.id.TimePicker);
        timePicker1= (ImageView) findViewById(R.id.TimePicker1);
        btn = (Button) findViewById(R.id.SubmitAddEvents);
        datePickerEdit= (EditText) findViewById(R.id.DatePickerEdit);
        timePickerEdit= (EditText) findViewById(R.id.TimePickerEdit);
        timePickerEdit1= (EditText) findViewById(R.id.TimePickerEdit1);
    }

    //timepicker1 clicked
    public void timePicker1Clicked(){

        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        timePickerEdit1.setText(getTime(hourOfDay,minute));

                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();

    }

    // ForMatting Time
    private String getTime(int hr,int min) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,hr);
        cal.set(Calendar.MINUTE,min);
        Format formatter;
        formatter = new SimpleDateFormat("h:mm a");
        return formatter.format(cal.getTime());
    }


    //timePicker Clicked
    public void timePickerClicked(){

        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        timePickerEdit.setText(getTime(hourOfDay,minute));
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();

    }

    //DatePicker Clicked
    public void DatePickerClicked(){

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        datePickerEdit.setText(year+ "-" + (monthOfYear + 1) + "-" +dayOfMonth);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }

    //Submitting clicked
    public void submitClicked(){

        String url=imageURLtx.getText().toString();
        String title=titletx.getText().toString();
        String desc=desctx.getText().toString();
        String date=datePickerEdit.getText().toString();
        String time=timePickerEdit.getText().toString()+" - "+timePickerEdit1.getText().toString();

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
