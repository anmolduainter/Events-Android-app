package com.example.anmol.events.Adapter;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anmol.events.Events.RegisteredEvents;
import com.example.anmol.events.Login;
import com.example.anmol.events.PostData.RegisterEvents;
import com.example.anmol.events.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by anmol on 1/8/17.
 */

public class RecyclerRegEvents extends RecyclerView.Adapter<RecyclerRegEvents.ViewHolder>  {


    List<String> imgUrl,name,date,time,desc;
    Context ctx;

    public RecyclerRegEvents(Context ctx, List<String> imgUrl, List<String> name, List<String> date, List<String> time, List<String> desc) {

        this.imgUrl=imgUrl;
        this.name=name;
        this.date=date;
        this.time=time;
        this.desc=desc;
        this.ctx=ctx;
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        TextView name1,date1,time1,desc1;
        ImageView img1;
        RelativeLayout rel1;
        Button Register1,BookMark;

        public ViewHolder(View itemView)
        {
            super(itemView);
            rel1=itemView.findViewById(R.id.RegEventRel);
            img1=itemView.findViewById(R.id.RegEventsImage);
            name1=itemView.findViewById(R.id.RegName);
            date1=itemView.findViewById(R.id.RegDate);
            time1=itemView.findViewById(R.id.RegTime);
            desc1=itemView.findViewById(R.id.RegDesc);
            Register1=itemView.findViewById(R.id.Not_Register_Reg_Events_Button);

            }


        }


    @Override
    public RecyclerRegEvents.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new RecyclerRegEvents.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_reg_events,parent,false));

    }

    @Override
    public void onBindViewHolder(RecyclerRegEvents.ViewHolder holder, int position) {


        Picasso.with(ctx).load(imgUrl.get(position)).fit().into(holder.img1);

        holder.name1.setText(name.get(position));
        holder.date1.setText(date.get(position));
        holder.time1.setText(time.get(position));
        holder.desc1.setText(desc.get(position));


    }

    @Override
    public int getItemCount() {

        return imgUrl.size();

    }


}
