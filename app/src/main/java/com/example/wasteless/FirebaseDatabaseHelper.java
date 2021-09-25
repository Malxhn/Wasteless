package com.example.wasteless;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import  com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FirebaseDatabaseHelper {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceOfferItems;
    private List<OfferItem> offeritems = new ArrayList<>();

    public interface DataStatus{

        void DataIsLoaded (List<OfferItem> offeritems,List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();

    }

    public FirebaseDatabaseHelper() {

        mDatabase=FirebaseDatabase.getInstance();
        mReferenceOfferItems=mDatabase.getReference("OfferItem");
    }

    public void readOfferItems(final DataStatus dataStatus)
    {
        mReferenceOfferItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                offeritems.clear();
                List<String> keys= new ArrayList<>();
                for(DataSnapshot keyNode:datasnapshot.getChildren())
                {
                    keys.add(keyNode.getKey());
                    OfferItem offeritem = keyNode.getValue(OfferItem.class);
                    offeritems.add(offeritem);

                }
                dataStatus.DataIsLoaded(offeritems,keys);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void addOfferItem(OfferItem offeritem,final DataStatus dataStatus)
    {
        String key= mReferenceOfferItems.push().getKey();
        mReferenceOfferItems.child(key).setValue(offeritem).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsInserted();

            }
        });
    }
    public void updateOfferItem(String key,OfferItem offeritem,final DataStatus dataStatus)
    {
        mReferenceOfferItems.child(key).setValue(offeritem)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdated();

                    }
                });
    }
    public void deleteOfferItem(String key,final DataStatus dataStatus)
    {
        mReferenceOfferItems.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid)
                    {
                        dataStatus.DataIsDeleted();
                    }
                });
    }
}
