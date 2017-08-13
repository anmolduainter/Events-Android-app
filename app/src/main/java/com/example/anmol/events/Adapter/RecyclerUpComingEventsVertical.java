package com.example.anmol.events.Adapter;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.anmol.events.Events.DetailsOfEvents;
import com.example.anmol.events.Login;
import com.example.anmol.events.PostData.RegisterEvents;
import com.example.anmol.events.R;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by anmol on 13/8/17.
 */

public class RecyclerUpComingEventsVertical extends RecyclerView.Adapter<RecyclerUpComingEventsVertical.ViewHolder> {


    List<String> imgUrl,name,date,time,desc,phone,username;
    boolean LoggedIn,Today;
    Context ctx;
    ImageView imageView;

    String s[]={"UpComing Events","Today Events"};

    List<List<List<String>>> ResultingList;


    public RecyclerUpComingEventsVertical(boolean LoggedIn,boolean Today, Context ctx,List<List<List<String>>> ResultingList) {


        this.Today=Today;
        this.ResultingList=ResultingList;
        this.ctx=ctx;
        this.LoggedIn=LoggedIn;

    }


    class ViewHolder extends RecyclerView.ViewHolder{


        TextView tx;
        RelativeLayout relativeLayout;
        RecyclerView rv;
        RecyclerView.LayoutManager layoutManager;
        RecyclerView.Adapter adapter;

        public ViewHolder(View itemView)
        {
            super(itemView);

            relativeLayout=itemView.findViewById(R.id.AllEventRel);
            tx=itemView.findViewById(R.id.TextViewContent);
            rv=itemView.findViewById(R.id.UpcomingEventsMainScreenSecondRecycler);
            layoutManager=new LinearLayoutManager(ctx,LinearLayoutManager.HORIZONTAL,false);
           // rv.setLayoutManager(layoutManager);
           // rv.setAdapter(adapter);


        }


    }


    @Override
    public RecyclerUpComingEventsVertical.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new RecyclerUpComingEventsVertical.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_main_vertical,parent,false));

    }

    @Override
    public void onBindViewHolder(RecyclerUpComingEventsVertical.ViewHolder holder, int position) {


//        Picasso.with(ctx).load(imgUrl.get(position)).into(holder.img1);
//        holder.name1.setText(name.get(position));

//        holder.date1.setText(date.get(position));
//        holder.time1.setText(time.get(position));
//        holder.desc1.setText(desc.get(position));

        holder.tx.setText(s[position]);
        holder.rv.setLayoutManager(holder.layoutManager);

        imgUrl=ResultingList.get(position).get(0);
        name=ResultingList.get(position).get(1);
        date=ResultingList.get(position).get(2);
        time=ResultingList.get(position).get(3);
        desc=ResultingList.get(position).get(4);
        phone=ResultingList.get(position).get(5);
        username=ResultingList.get(position).get(6);

        holder.adapter=new RecyclerUpcomingEventsMain(holder.relativeLayout,LoggedIn,ctx,imgUrl,name,date,time, desc,phone, username,Today);
        holder.rv.setAdapter(holder.adapter);


    }

    @Override
    public int getItemCount() {

        return 2;

    }
}

