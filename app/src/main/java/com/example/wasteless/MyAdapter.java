package com.example.wasteless;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<OfferHolder> {


    private Context mContext;
    private List<OfferItem> ourOfferList;

    public MyAdapter(Context mContext, List<OfferItem> ourOfferList) {
        this.mContext = mContext;
        this.ourOfferList = ourOfferList;
    }

    @Override
    public OfferHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.oneoffer,parent,false);


        return new OfferHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferHolder holder, int position) {

        // Glide.with(mContext)
        //.load(ourOfferList.get(position).getItemImage())
        // .into(holder.img_offerimage);
        //  holder.img_offerimage.setImageResource(ourOfferList.get(position).getItemImage());
        holder.txt_offertitle.setText(ourOfferList.get(position).getOfferName());
        holder.txt_offerdescription.setText(ourOfferList.get(position).getDescription());

        holder.cardview_itemcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,OfferDetailActivity.class);
                // intent.putExtra("Image" , ourOfferList.get(holder.getAdapterPosition()).getItemImage());
                intent.putExtra("Description",ourOfferList.get(holder.getAdapterPosition()).getDescription());
                intent.putExtra("Address",ourOfferList.get(holder.getAdapterPosition()).getAddress());
                intent.putExtra("Category",ourOfferList.get(holder.getAdapterPosition()).getCategory());
                intent.putExtra("OfferName",ourOfferList.get(holder.getAdapterPosition()).getOfferName());
                intent.putExtra("PickUp_By",ourOfferList.get(holder.getAdapterPosition()).getPickUp_By());
                intent.putExtra("PickUp_From",ourOfferList.get(holder.getAdapterPosition()).getPickUp_From());
                intent.putExtra("Servings",ourOfferList.get(holder.getAdapterPosition()).getServings());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ourOfferList.size();
    }

    public void filteredList(ArrayList<OfferItem> filterList) {
        ourOfferList = filterList;
        notifyDataSetChanged();
    }
}
class OfferHolder extends RecyclerView.ViewHolder{

    ImageView img_offerimage;
    TextView txt_offertitle , txt_offerdescription;
    CardView cardview_itemcard;


    public OfferHolder(View itemView) {
        super(itemView);

        img_offerimage = itemView.findViewById(R.id.img_offerimage);
        txt_offertitle = itemView.findViewById(R.id.txt_offertitle);
        txt_offerdescription = itemView.findViewById(R.id.txt_offerdescription);
        cardview_itemcard=itemView.findViewById(R.id.cardview_itemcard);

    }
}