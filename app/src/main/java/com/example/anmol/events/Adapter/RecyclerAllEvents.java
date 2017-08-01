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
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.example.anmol.events.Login;
import com.example.anmol.events.MainActivity;
import com.example.anmol.events.PostData.RegisterEvents;
import com.example.anmol.events.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by anmol on 27/7/17.
 */

public class RecyclerAllEvents extends RecyclerView.Adapter<RecyclerAllEvents.ViewHolder> {


    List<String> imgUrl,name,date,time,desc,phone,username;
    boolean LoggedIn;
    Context ctx;

    public RecyclerAllEvents(boolean LoggedIn,Context ctx, List<String> imgUrl, List<String> name, List<String> date, List<String> time, List<String> desc, List<String> phone, List<String> username) {

        this.imgUrl=imgUrl;
        this.name=name;
        this.date=date;
        this.time=time;
        this.desc=desc;
        this.phone=phone;
        this.username=username;
        this.ctx=ctx;
        this.LoggedIn=LoggedIn;

    }


    class ViewHolder extends RecyclerView.ViewHolder{

        TextView name1,date1,time1,desc1;
        ImageView img1;
        RelativeLayout rel1;
        Button Register1,BookMark;

        public ViewHolder(View itemView)
        {
            super(itemView);
            rel1=itemView.findViewById(R.id.AllEventRel);
            img1=itemView.findViewById(R.id.AllEventsImage);
            name1=itemView.findViewById(R.id.Name);
            date1=itemView.findViewById(R.id.Date);
            time1=itemView.findViewById(R.id.Time);
            desc1=itemView.findViewById(R.id.Desc);
            Register1=itemView.findViewById(R.id.Register_All_Events_Button);
            BookMark=itemView.findViewById(R.id.BookMark_All_Events);

            Register1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {

                    int pos=getAdapterPosition();

                    if (LoggedIn){

                        RegisterEvents registerEvents=new RegisterEvents(ctx,name.get(pos),date.get(pos),time.get(pos),0);

                        registerEvents.POST(new RegisterEvents.AsyncCallBack() {
                            @Override
                            public void onSuccess(JSONObject jsonObject) throws JSONException {

                                boolean success=jsonObject.getBoolean("success");
                                if (success){
                                    int colorStart=view.getSolidColor();
                                    int colorEnd=Color.WHITE;
                                    ValueAnimator colorAnim= ObjectAnimator.ofInt(view,"backgroundColor",colorStart,colorEnd);
                                    colorAnim.setDuration(2000);
                                    colorAnim.setEvaluator(new ArgbEvaluator());
                                    colorAnim.start();
                                    Register1.setTextColor(Color.BLACK);
                                }
                                else{
                                    Toast.makeText(ctx,"Already Registered",Toast.LENGTH_LONG).show();
                                }

                            }
                        });

                    }
                    else{

                        Intent i =new Intent(ctx,Login.class);
                        ctx.startActivity(i);

                    }


                    Toast.makeText(ctx,String.valueOf(pos),Toast.LENGTH_LONG).show();

                }
            });

            if (LoggedIn){

                BookMark.setVisibility(View.VISIBLE);

                BookMark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        int pos=getAdapterPosition();

                        final RegisterEvents registerEvents=new RegisterEvents(ctx,name.get(pos),date.get(pos),time.get(pos),1);

                        registerEvents.POST(new RegisterEvents.AsyncCallBack() {
                            @Override
                            public void onSuccess(JSONObject jsonObject) throws JSONException {

                                boolean success=jsonObject.getBoolean("success");
                                if (success){
                                    BookMark.setBackgroundColor(Color.GRAY);
                                }
                                else{
                                    BookMark.setBackgroundColor(Color.WHITE);
                                }

                            }
                        });


                    }
                });

            }


        }
    }


    @Override
    public RecyclerAllEvents.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new RecyclerAllEvents.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_all_events,parent,false));

    }

    @Override
    public void onBindViewHolder(RecyclerAllEvents.ViewHolder holder, int position) {


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
