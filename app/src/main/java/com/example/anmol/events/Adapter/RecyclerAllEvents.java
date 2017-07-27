package com.example.anmol.events.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.anmol.events.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by anmol on 27/7/17.
 */

public class RecyclerAllEvents extends RecyclerView.Adapter<RecyclerAllEvents.ViewHolder> {


    List<String> imgUrl,name,date,time,desc,phone,username;


    public RecyclerAllEvents(List<String> imgUrl, List<String> name, List<String> date, List<String> time, List<String> desc, List<String> phone, List<String> username) {

        this.imgUrl=imgUrl;
        this.name=name;
        this.date=date;
        this.time=time;
        this.desc=desc;
        this.phone=phone;
        this.username=username;

    }


    class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,date,time,desc;
        ImageView img;
        RelativeLayout rel;

        public ViewHolder(View itemView)
        {
            super(itemView);
            rel=itemView.findViewById(R.id.AllEventRel);
            name=itemView.findViewById(R.id.Name);
            date=itemView.findViewById(R.id.Date);
            time=itemView.findViewById(R.id.Time);
            desc=itemView.findViewById(R.id.Desc);
        }
    }


    @Override
    public RecyclerAllEvents.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new RecyclerAllEvents.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_all_events,parent,false));

    }

    @Override
    public void onBindViewHolder(RecyclerAllEvents.ViewHolder holder, int position) {

        holder.name.setText(name.get(position));


    }

    @Override
    public int getItemCount() {

        return imgUrl.size();

    }
}
