package com.example.anmol.events.Events;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import com.example.anmol.events.Data.yourEvents;
import com.example.anmol.events.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class YourEvents extends AppCompatActivity{

    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.your_events_main);

        //initialize views
        initViews();

        //getting yourEvents
        yourEvents y=new yourEvents(YourEvents.this);
        y.EventsYours(new yourEvents.Callback() {
            @Override
            public void success(JSONObject jsonObject) throws JSONException {

                List<String> imgUrl=new ArrayList<String>();
                List<String> name=new ArrayList<String>();
                List<String> date=new ArrayList<String>();
                List<String> time=new ArrayList<String>();
                List<String> desc=new ArrayList<String>();


                //getting jsonArray
                JSONArray jsonArray=jsonObject.getJSONArray("result");

                for (int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    imgUrl.add(jsonObject1.getString("imgUrl"));
                    name.add(jsonObject1.getString("name"));
                    date.add(jsonObject1.getString("date"));
                    time.add(jsonObject1.getString("time"));
                    desc.add(jsonObject1.getString("desc"));
                }

                //Setting Up Recycler View

            }
        });
    }

    // Method to initialize views
    public void initViews(){
        rv= (RecyclerView) findViewById(R.id.recyclerViewYours);
    }
}
