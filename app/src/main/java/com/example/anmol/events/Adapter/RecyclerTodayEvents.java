package com.example.anmol.events.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.anmol.events.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RecyclerTodayEvents extends RecyclerView.Adapter<RecyclerTodayEvents.ViewHolder>  {


    List<String> imgUrl,name,date,time,desc;
    List<String> timeArr;
    Context ctx;
    boolean loggedIn;

    public RecyclerTodayEvents(boolean loggedIn, Context ctx, List<String> imgUrl, List<String> name, List<String> date, List<String> time, List<String> desc, List<String> timeArr) {

        this.imgUrl=imgUrl;
        this.name=name;
        this.date=date;
        this.time=time;
        this.desc=desc;
        this.ctx=ctx;
        this.timeArr=timeArr;
        this.loggedIn=loggedIn;

    }


    class ViewHolder extends RecyclerView.ViewHolder{

        TextView name1,date1,time1,timegoingOn;
        ImageView img1;
        RelativeLayout rel1;
        Button Register1,Like,NotLike;

        public ViewHolder(View itemView)
        {
            super(itemView);
            rel1=itemView.findViewById(R.id.TimeEventRel);
            img1=itemView.findViewById(R.id.TimeEventsImage);
            name1=itemView.findViewById(R.id.TimeName);
            date1=itemView.findViewById(R.id.TimeDate);
            time1=itemView.findViewById(R.id.TimeTime);
            timegoingOn=itemView.findViewById(R.id.TimeGoingon);
            Like=itemView.findViewById(R.id.Like_Time_Events_Button);
            NotLike=itemView.findViewById(R.id.Not_Like_Time_Events_Button);
        }



    }


    @Override
    public RecyclerTodayEvents.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new RecyclerTodayEvents.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_today_events,parent,false));

    }

    @Override
    public void onBindViewHolder(RecyclerTodayEvents.ViewHolder holder, int position) {


        Picasso.with(ctx).load(imgUrl.get(position)).fit().into(holder.img1);

        holder.name1.setText(name.get(position));
        holder.date1.setText(date.get(position));
        holder.time1.setText(time.get(position));
    //    holder.desc1.setText(desc.get(position));


    }

    @Override
    public int getItemCount() {

        return imgUrl.size();

    }


}

