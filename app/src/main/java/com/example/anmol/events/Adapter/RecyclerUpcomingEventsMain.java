package com.example.anmol.events.Adapter;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
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
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.anmol.events.Events.DetailsOfEvents;
import com.example.anmol.events.Login;
import com.example.anmol.events.PostData.RegisterEvents;
import com.example.anmol.events.R;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;


public class RecyclerUpcomingEventsMain extends RecyclerView.Adapter<RecyclerUpcomingEventsMain.ViewHolder> {


    List<String> imgUrl,name,date,time,desc,phone,username;
    boolean LoggedIn;
    Context ctx;
    ImageView imageView;
    RelativeLayout relativeLayout;
    boolean today;

    public RecyclerUpcomingEventsMain(RelativeLayout relativeLayout, boolean LoggedIn, Context ctx, List<String> imgUrl, List<String> name, List<String> date, List<String> time, List<String> desc, List<String> phone, List<String> username, boolean today) {


        this.today=today;
        this.imgUrl=imgUrl;
        this.name=name;
        this.date=date;
        this.time=time;
        this.desc=desc;
        this.phone=phone;
        this.username=username;
        this.ctx=ctx;
        this.LoggedIn=LoggedIn;
        this.relativeLayout=relativeLayout;

    }


    class ViewHolder extends RecyclerView.ViewHolder{

        TextView name1,date1,time1;
        ImageView img1;
        RelativeLayout rel1;
        Button Register1,BookMark;

        public ViewHolder(View itemView)
        {
            super(itemView);
            rel1=itemView.findViewById(R.id.AllEventRel);
            img1=itemView.findViewById(R.id.AllEventsImage1);
            name1=itemView.findViewById(R.id.Name1);
            date1=itemView.findViewById(R.id.Date1);
            time1=itemView.findViewById(R.id.Time1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View view) {

                    int position=getAdapterPosition();

                    Intent i=new Intent(ctx, DetailsOfEvents.class);
                    i.putExtra("ImageUrl",imgUrl.get(position));
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation((Activity) ctx,img1,"Event_Photo");
                    ctx.startActivity(i,options.toBundle());

                }
            });

        }


    }


    @Override
    public RecyclerUpcomingEventsMain.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new RecyclerUpcomingEventsMain.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_upcoming_events,parent,false));

    }

    @Override
    public void onBindViewHolder(RecyclerUpcomingEventsMain.ViewHolder holder, int position) {


        Picasso.with(ctx).load(imgUrl.get(position)).into(holder.img1);
        holder.name1.setText(name.get(position));

       holder.date1.setText(date.get(position));
        holder.time1.setText(time.get(position));
//        holder.desc1.setText(desc.get(position));


    }

    @Override
    public int getItemCount() {

        return imgUrl.size();

    }
}

