package com.example.anmol.events.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.example.anmol.events.R;

/**
 * Created by anmol on 27/7/17.
 */

public class RecyclerAllEvents extends RecyclerView.Adapter<RecyclerAllEvents.ViewHolder> {


    public RecyclerAllEvents() {



    }


    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tx;

        public ViewHolder(View itemView)
        {
            super(itemView);
            tx=itemView.findViewById(R.id.textCardNav);
        }
    }


    @Override
    public RecyclerAllEvents.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new RecyclerAllEvents.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_navigation_main,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerAllEvents.ViewHolder holder, int position) {

   //     holder.tx.setText(s[position]);

    }

    @Override
    public int getItemCount() {

        return 0;

    }
}
