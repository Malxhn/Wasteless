package com.example.wasteless;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private OfferItemsAdapter mOfferItemsAdapter;
    public void setConfig(RecyclerView recyclerView,Context context,List<OfferItem> offeritems,List<String> keys)
    {
        mContext=context;
        mOfferItemsAdapter=new OfferItemsAdapter(offeritems,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mOfferItemsAdapter);

    }




    class OfferItemView extends RecyclerView.ViewHolder{
        private TextView mOfferName;
        private TextView mCategory;
        private  TextView mDescription;
        private  TextView mAddress;
        private  TextView mPickUp_By;
        private  TextView mPickUp_From;
        private  TextView mReqOfferEmail;
        private  TextView mReqOfferName;
        private TextView mReqOfferPhone;
        private  TextView mServings;

        private  String key;

        public OfferItemView (ViewGroup parent)
        {
            super(LayoutInflater.from(mContext).inflate(R.layout.offer_list_item,parent,false));

            mOfferName =(TextView) itemView.findViewById(R.id.offername_txtView);
            mCategory =(TextView) itemView.findViewById(R.id.category_txtView);
            mDescription =(TextView) itemView.findViewById(R.id.description_txtView);
            mAddress =(TextView) itemView.findViewById(R.id.address_txtView);
            mPickUp_By =(TextView) itemView.findViewById(R.id.pickupby_txtView);
            mPickUp_From =(TextView) itemView.findViewById(R.id.pickupfrom_txtView);
            mReqOfferEmail =(TextView) itemView.findViewById(R.id.reqofferemail_txtView);
            mReqOfferName =(TextView) itemView.findViewById(R.id.reqoffername_txtView);
            mReqOfferPhone =(TextView) itemView.findViewById(R.id.reqofferphone_txtView);
            mServings =(TextView) itemView.findViewById(R.id.servings_txtView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(mContext,OfferItemDetailsActivity.class);
                    intent.putExtra("key",key);
                    intent.putExtra("offername",mOfferName.getText().toString());
                    intent.putExtra("address",mAddress.getText().toString());
                    intent.putExtra("description",mDescription.getText().toString());
                    intent.putExtra("pickupby",mPickUp_By.getText().toString());
                    intent.putExtra("pickupfrom",mPickUp_From.getText().toString());
                    intent.putExtra("servings",mServings.getText().toString());
                    intent.putExtra("category",mCategory.getText().toString());

                    mContext.startActivity(intent);



                }
            });




        }
        public void bind(OfferItem offeritem,String key)
        {
            mOfferName.setText(offeritem.getOfferName());
            mCategory.setText(offeritem.getCategory());
            mDescription.setText(offeritem.getDescription());
            mAddress.setText(offeritem.getAddress());
            mPickUp_By.setText(offeritem.getPickUp_By());
            mPickUp_From.setText(offeritem.getPickUp_From());
            mReqOfferEmail.setText(offeritem.getReqOfferEmail());
            mReqOfferName.setText(offeritem.getOfferName());
            mReqOfferPhone.setText(offeritem.getReqOfferPhone());
            mServings.setText(offeritem.getServings());
            this.key=key;



        }



    }
    class OfferItemsAdapter extends RecyclerView.Adapter<OfferItemView>{
        private List<OfferItem> mOfferItemList;
        private List<String> mKeys;

        public OfferItemsAdapter(List<OfferItem> mOfferItemList, List<String> mKeys) {
            this.mOfferItemList = mOfferItemList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public OfferItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new OfferItemView(parent) ;
        }

        @Override
        public void onBindViewHolder(@NonNull OfferItemView holder, int position) {
            holder.bind(mOfferItemList.get(position),mKeys.get(position));

        }

        @Override
        public int getItemCount() {
            return mOfferItemList.size();
        }
    }
}
