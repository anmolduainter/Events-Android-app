package com.example.anmol.events.Adapter.EventsHigh;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.anmol.events.OnSiteWeb.onsite;
import com.example.anmol.events.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by anmol on 15/8/17.
 */

public class RecyclerOutDoorAdapter extends RecyclerView.Adapter<RecyclerOutDoorAdapter.ViewHolder> {


    Context ctx;
    List<String> image;
    List<String> href;
    List<String> title;
    List<String> dateTime;
    List<String> evenue;
    List<String> genre;

    public RecyclerOutDoorAdapter(Context ctx, List<String> image, List<String> href, List<String> title, List<String> dateTime, List<String> evenue, List<String> genre) {

        this.ctx=ctx;
        this.image=image;
        this.href=href;
        this.title=title;
        this.dateTime=dateTime;
        this.evenue=evenue;
        this.genre=genre;

    }


    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView titl,dateTim,evenu,genr;

        RelativeLayout rv;

        public ViewHolder(View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.EventsHighTodayImage);
            titl=itemView.findViewById(R.id.EventsHighTodayTitle);
            dateTim=itemView.findViewById(R.id.EventsHighTodayDateTime);
          //  evenu=itemView.findViewById(R.id.EventsHighTodayEvenue);
            genr=itemView.findViewById(R.id.EventsHighTodayGenre);

            rv=itemView.findViewById(R.id.RelativeMainEventsHighToday);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos=getAdapterPosition();

                    Intent i=new Intent(ctx,onsite.class);
                    i.putExtra("link",href.get(pos));
                    ctx.startActivity(i);

                }
            });

        }
    }


    @Override
    public RecyclerOutDoorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new RecyclerOutDoorAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_events_high_today,parent,false));

    }

    @Override
    public void onBindViewHolder(final RecyclerOutDoorAdapter.ViewHolder holder, int position) {



        Picasso.with(ctx).load(image.get(position)).into(holder.img);

        Glide.with(ctx).load(image.get(position)).centerCrop().bitmapTransform(new BlurTransformation(ctx,50)).into(new SimpleTarget<GlideDrawable>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                holder.rv.setBackground(resource);
            }
        });


        holder.titl.setText(title.get(position));

        holder.dateTim.setText(dateTime.get(position));

        holder.evenu.setText(evenue.get(position));

        holder.genr.setText(genre.get(position));

    }

    @Override
    public int getItemCount() {

        return image.size();

    }
}

