package com.example.anmol.events.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.anmol.events.Adapter.BookMyShow.BookMyShowAdapter;
import com.example.anmol.events.Adapter.EventsHigh.RecyclerOutDoorAdapter;
import com.example.anmol.events.Adapter.EventsHigh.RecyclerTechnologyAdapter;
import com.example.anmol.events.Adapter.EventsHigh.RecyclerTodayAdapter;
import com.example.anmol.events.Adapter.Insider.InsiderAdapter;
import com.example.anmol.events.Data.BookMyShow.bookMyShowData;
import com.example.anmol.events.Data.EventsHigh.OutDoorsEventsHigh;
import com.example.anmol.events.Data.EventsHigh.TechnologyEventsHigh;
import com.example.anmol.events.Data.EventsHigh.TodayEventsHigh;
import com.example.anmol.events.Data.Insider.InsiderData;
import com.example.anmol.events.R;
import java.util.ArrayList;
import java.util.List;



public class RecyclerUpComingEventsVertical extends RecyclerView.Adapter<RecyclerUpComingEventsVertical.ViewHolder> {


    List<String> imgUrl,name,date,time,desc,phone,username;
    boolean LoggedIn,Today;
    Context ctx;
    ImageView imageView;

    String s[]={"BookMyShow","Insider Events","Technology(Events High)","OutDoor(Events High)","Events High(Today)"};

    List<List<List<String>>> ResultingList;


    public RecyclerUpComingEventsVertical(Context ctx) {


        this.ctx=ctx;

    }


    class ViewHolder extends RecyclerView.ViewHolder{


        TextView tx;
        RelativeLayout relativeLayout;
        RecyclerView rv;
        RecyclerView.LayoutManager layoutManager;
        RecyclerView.Adapter adapter;

        public ViewHolder(View itemView)
        {
            super(itemView);

            relativeLayout=itemView.findViewById(R.id.AllEventRel);
            tx=itemView.findViewById(R.id.TextViewContent);
            rv=itemView.findViewById(R.id.UpcomingEventsMainScreenSecondRecycler);
            layoutManager=new LinearLayoutManager(ctx,LinearLayoutManager.HORIZONTAL,false);
           // rv.setLayoutManager(layoutManager);
           // rv.setAdapter(adapter);


        }


    }


    @Override
    public RecyclerUpComingEventsVertical.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new RecyclerUpComingEventsVertical.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_main_vertical,parent,false));

    }

    @Override
    public void onBindViewHolder(final RecyclerUpComingEventsVertical.ViewHolder holder, int position) {


        holder.tx.setText(s[position]);
        holder.rv.setLayoutManager(holder.layoutManager);

        // Horizontal Recycler View

        // Step By Step

//        if (position==0){
//
//            imgUrl=ResultingList.get(position).get(0);
//            name=ResultingList.get(position).get(1);
//            date=ResultingList.get(position).get(2);
//            time=ResultingList.get(position).get(3);
//            desc=ResultingList.get(position).get(4);
//            phone=ResultingList.get(position).get(5);
//            username=ResultingList.get(position).get(6);
//
//            holder.adapter=new RecyclerUpcomingEventsMain(holder.relativeLayout,LoggedIn,ctx,imgUrl,name,date,time, desc,phone, username,Today);
//            holder.rv.setAdapter(holder.adapter);
//
//
//        }
//        else if (position==1){
//
//            if (Today){
//                imgUrl=ResultingList.get(position).get(0);
//                name=ResultingList.get(position).get(1);
//                date=ResultingList.get(position).get(2);
//                time=ResultingList.get(position).get(3);
//                desc=ResultingList.get(position).get(4);
//                phone=ResultingList.get(position).get(5);
//                username=ResultingList.get(position).get(6);
//            }
//            else{
//
//
//                imgUrl=new ArrayList<>();
//                name=new ArrayList<>();
//                date=new ArrayList<>();
//                time=new ArrayList<>();
//                desc=new ArrayList<>();
//                phone=new ArrayList<>();
//                username=new ArrayList<>();
//
//
//            }
//
//
//            holder.adapter=new RecyclerUpcomingEventsMain(holder.relativeLayout,LoggedIn,ctx,imgUrl,name,date,time, desc,phone, username,Today);
//            holder.rv.setAdapter(holder.adapter);
//
//
//        }

        // Book My Show Event
        if (position==0){

            bookMyShowData bookMyShowData=new bookMyShowData(ctx);
            bookMyShowData.getData(new bookMyShowData.bookMyShowcallback() {
                @Override
                public void getData(List<List<String>> li) {

                    List<String> image=li.get(0);
                    List<String> titleL=li.get(1);
                    List<String> dateL=li.get(2);
                    List<String> tagL=li.get(3);
                    List<String> buyNowL=li.get(4);

                    System.out.println("");

                    holder.adapter=new BookMyShowAdapter(ctx,image,titleL,dateL,tagL,buyNowL);
                    holder.rv.setAdapter(holder.adapter);

                }
            });


        }

        //Insider Events Data
        else if (position==1){

            InsiderData insiderData=new InsiderData(ctx);
            insiderData.getDataInsider(new InsiderData.InsiderCallBack() {
                @Override
                public void result(List<List<String>> list) {

                    List<String> name=list.get(0);
                    List<String> image=list.get(1);
                    List<String> date=list.get(2);
                    List<String> place=list.get(3);

                    holder.adapter=new InsiderAdapter(ctx,image,name,date,place);
                    holder.rv.setAdapter(holder.adapter);

                }
            });

        }

        else if (position==2){


//            TechnologyEventsHigh technologyEventsHigh=new TechnologyEventsHigh(ctx);
//
//            technologyEventsHigh.getData(new TechnologyEventsHigh.AsyncCallback() {
//                @Override
//                public void onSuccess(List<List<String>> list) {
//
//                    holder.adapter=new RecyclerTechnologyAdapter(ctx,list.get(0),list.get(1),list.get(2),list.get(3),list.get(4),list.get(5));
//                    holder.rv.setAdapter(holder.adapter);
//
//
//                }
//            });


        }


        else if (position==3){

//
//            OutDoorsEventsHigh outDoorsEventsHigh=new OutDoorsEventsHigh(ctx);
//
//            outDoorsEventsHigh.getData(new OutDoorsEventsHigh.AsyncCallback() {
//                @Override
//                public void onSuccess(List<List<String>> list) {
//
//                    holder.adapter=new RecyclerOutDoorAdapter(ctx,list.get(0),list.get(1),list.get(2),list.get(3),list.get(4),list.get(5));
//                    holder.rv.setAdapter(holder.adapter);
//
//                }
//            });
        }

        else if (position==4){


//            TodayEventsHigh todayEventsHigh=new TodayEventsHigh(ctx);
//
//            todayEventsHigh.getData(new TodayEventsHigh.AsyncCallback() {
//                @Override
//                public void onSuccess(List<List<String>> list) {
//
//                    holder.adapter=new RecyclerTodayAdapter(ctx,list.get(0),list.get(1),list.get(2),list.get(3),list.get(4),list.get(5));
//                    holder.rv.setAdapter(holder.adapter);
//
//
//                }
//            });
        }


    }

    @Override
    public int getItemCount() {

        return 5;

    }
}

