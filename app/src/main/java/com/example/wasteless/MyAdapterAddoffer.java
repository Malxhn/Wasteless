package com.example.wasteless;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapterAddoffer extends RecyclerView.Adapter<MyAdapterAddoffer.MyViewHolder> {


    Context context;
    ArrayList<OfferItem> list;

    public MyAdapterAddoffer(Context context, ArrayList<OfferItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        OfferItem offerItem =list.get(position);
        Picasso.get().load(offerItem.getmImageUrl())
                .fit()
                .centerCrop()
                .into(holder.imageView);
        holder.offername.setText(offerItem.getOfferName());
        holder.servings.setText(String.valueOf(offerItem.getServings()));
        holder.address.setText(offerItem.getAddress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView offername,servings,address;
        public ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.image_view_upload);
            offername=itemView.findViewById(R.id.tv_offername);
            servings=itemView.findViewById(R.id.tv_servings);
            address=itemView.findViewById(R.id.tv_address);


        }
    }
}
