package com.example.anmol.events.Adapter.BookMyShow;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.anmol.events.R;
import com.squareup.picasso.Picasso;

import java.util.List;


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
            evenu=itemView.findViewById(R.id.EventsHighTodayEvenue);
            genr=itemView.findViewById(R.id.EventsHighTodayGenre);

            rv=itemView.findViewById(R.id.RelativeMainEventsHighToday);

        }
    }


    @Override
    public BookMyShowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new BookMyShowAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_events_high_today,parent,false));

    }

    @Override
    public void onBindViewHolder(final BookMyShowAdapter.ViewHolder holder, int position) {

        //Setting Image
        Picasso.with(ctx).load(image.get(position)).fit().into(holder.img);

        //Setting title
        holder.titl.setText(title.get(position));



    }

    @Override
    public int getItemCount() {

        return image.size();

    }
}

