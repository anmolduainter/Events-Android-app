package com.example.anmol.events.Adapter.BookMyShow;

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


public class BookMyShowAdapter extends RecyclerView.Adapter<BookMyShowAdapter.ViewHolder> {


    Context ctx;
    private List<String> image,title,date,tag,buyNow;

    public BookMyShowAdapter(Context ctx,List<String> image,List<String> title,List<String> date,List<String> tag,List<String> buyNow){

        this.ctx=ctx;
        this.image=image;
        this.title=title;
        this.date=date;
        this.tag=tag;
        this.buyNow=buyNow;

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
         //   evenu=itemView.findViewById(R.id.EventsHighTodayEvenue);
            genr=itemView.findViewById(R.id.EventsHighTodayGenre);

            rv=itemView.findViewById(R.id.RelativeMainEventsHighToday);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos=getAdapterPosition();

                    Intent i=new Intent(ctx,onsite.class);
                    i.putExtra("link","https://in.bookmyshow.com/"+buyNow.get(pos).replace("//","/"));
                    ctx.startActivity(i);

                }
            });

        }
    }


    @Override
    public BookMyShowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new BookMyShowAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_events_high_today,parent,false));

    }

    @Override
    public void onBindViewHolder(final BookMyShowAdapter.ViewHolder holder, int position) {

        //Setting Image
        Picasso.with(ctx).load("https:" + image.get(position)).fit().into(holder.img);

        Glide.with(ctx).load("https:" + image.get(position)).centerCrop().bitmapTransform(new BlurTransformation(ctx,50)).into(new SimpleTarget<GlideDrawable>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                holder.rv.setBackground(resource);
            }
        });

        //Setting title
        holder.titl.setText(title.get(position));

        //Setting date
        holder.dateTim.setText(date.get(position));

        //Setting Tag
        holder.genr.setText(tag.get(position));



    }

    @Override
    public int getItemCount() {

        return image.size();

    }
}

