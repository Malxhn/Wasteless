package com.example.wasteless;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class clotheslist extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapterAddoffer myAdapterAddoffer;
    ArrayList<OfferItem> list;

    String cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clotheslist);

        recyclerView=findViewById(R.id.rv_clothes);
        database= FirebaseDatabase.getInstance("https://wasteless-88bc9-default-rtdb.firebaseio.com/").getReference().child("OfferItem");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        myAdapterAddoffer =new MyAdapterAddoffer(this,list);
        recyclerView.setAdapter(myAdapterAddoffer);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    cat=dataSnapshot.child("category").getValue().toString();

                    if(cat.contentEquals("Clothes")) {
                        OfferItem offerItem = dataSnapshot.getValue(OfferItem.class);
                        list.add(offerItem);
                    }

                }
                myAdapterAddoffer.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}