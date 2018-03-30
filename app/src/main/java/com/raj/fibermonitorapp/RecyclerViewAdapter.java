


package com.raj.fibermonitorapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by RAJ on 25-03-2018.
 */


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Holder> {

    private LayoutInflater inflater;
    private List<Notifications> list= Collections.emptyList();
    Context context;

    RecyclerViewAdapter(Context context, List<Notifications> list) {
        inflater=LayoutInflater.from(context);
        this.list=list;
        this.context=context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=inflater.inflate(R.layout.card_view,parent,false);
        Holder holder=new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        Notifications data=list.get(list.size()-1-position);    ////For adding the Notifications at the top
        holder.cardViewText.setText(data.msg);

        holder.cardLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context,String.valueOf(position),Toast.LENGTH_SHORT).show();
            }
        });

        holder.cardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    //Toast.makeText(context,"Checked"+String.valueOf(position),Toast.LENGTH_SHORT).show();
                    list.remove(list.size()-1-position);   //////For Removing the cards from the stack when the checkbox is ticked
                    notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();

    }



    ////////Holder for the Recycler View

    public class Holder extends RecyclerView.ViewHolder
    {

        TextView cardViewText;
        LinearLayout cardLinearLayout;
        Button cardButton;
        public Holder(View itemView) {
            super(itemView);
            cardViewText=itemView.findViewById(R.id.cardViewText);
            cardLinearLayout=itemView.findViewById(R.id.cardLinearLayout);
            cardButton=itemView.findViewById(R.id.cardButton);
        }

    }




}

