package com.example.anmol.events.Adapter;

import android.support.transition.Transition;
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

public class RecyclerNavAdapter extends RecyclerView.Adapter<RecyclerNavAdapter.ViewHolder> {


    String[] s={"All Events","Today Events"};

    int height;

    public RecyclerNavAdapter(int height) {

      this.height=height;

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
    public RecyclerNavAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_navigation_main,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerNavAdapter.ViewHolder holder, int position) {

       holder.tx.setText(s[position]);
        TranslateAnimation translateAnimation=new TranslateAnimation(0,0,height,0);
        translateAnimation.setDuration((position+1)*300);
        holder.itemView.startAnimation(translateAnimation);

    }

    @Override
    public int getItemCount() {
        return s.length;
    }
}
