package com.example.anmol.events.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anmol.events.PostData.deleteOne;
import com.example.anmol.events.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class RecyclerAdapterYours  extends RecyclerView.Adapter<RecyclerAdapterYours.ViewHolder>  {


    List<String> id,imgUrl,name,date,time,desc;
    Context ctx;

    public RecyclerAdapterYours( Context ctx,List<String> id, List<String> imgUrl, List<String> name, List<String> date, List<String> time, List<String> desc) {

        this.id=id;
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
            edit=itemView.findViewById(R.id.EditYourEvents);
            delete=itemView.findViewById(R.id.deleteYourEvents);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    new SweetAlertDialog(ctx, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Sure Want To Delete this Event ? ")
                            .setConfirmText("Yes")
                            .setCancelText("No")
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismissWithAnimation();
                                }
                            })
                            // Confirm
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    int pos=getAdapterPosition();
                                    deleteClicked(pos);
                                    sDialog.dismissWithAnimation();
                                }
                            })
                            .show();
                }
            });


            // Edit CLicked
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(ctx,"Under Development",Toast.LENGTH_LONG).show();

                }
            });

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

    //deleteClicked
    public void deleteClicked(int pos){

        deleteOne deleteOne=new deleteOne(ctx,id.get(pos),name.get(pos),date.get(pos),time.get(pos));
        deleteOne.Delete(new deleteOne.CallBack() {
            @Override
            public void onSuccess(JSONObject jsonObject) throws JSONException {

                System.out.println(jsonObject);
                if (jsonObject.getBoolean("success")){
                    ((Activity)ctx).finish();
                    ctx.startActivity(((Activity)ctx).getIntent());
                }
            }
        });
    }


}


