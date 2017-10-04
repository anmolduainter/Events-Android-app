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

public class RecyclerAdapterYours  extends RecyclerView.Adapter<RecyclerAdapterYours.ViewHolder>  {


    List<String> imgUrl,name,date,time,desc;
    Context ctx;

    public RecyclerAdapterYours( Context ctx, List<String> imgUrl, List<String> name, List<String> date, List<String> time, List<String> desc) {

        this.imgUrl=imgUrl;
        this.name=name;
        this.date=date;
        this.time=time;
        this.desc=desc;
        this.ctx=ctx;

    }


    class ViewHolder extends RecyclerView.ViewHolder{

        TextView title,date;
        ImageView im;
        Button edit,delete;

        public ViewHolder(View itemView)
        {
            super(itemView);

            im=itemView.findViewById(R.id.imageView_your_events);
            title=itemView.findViewById(R.id.title_yoursEvent);
            date=itemView.findViewById(R.id.date_yours_Events);

        }



    }


    @Override
    public RecyclerAdapterYours.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new RecyclerAdapterYours.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.your_events_card,parent,false));

    }

    @Override
    public void onBindViewHolder(RecyclerAdapterYours.ViewHolder holder, int position) {

        Picasso.with(ctx).load(imgUrl.get(position)).fit().into(holder.im);
        holder.title.setText(name.get(position));
        holder.date.setText(date.get(position));

    }

    @Override
    public int getItemCount() {

        return imgUrl.size();

    }


}


