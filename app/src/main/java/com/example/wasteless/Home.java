package com.example.wasteless;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Home extends AppCompatActivity {
    RecyclerView recylerview_home;
    List<OfferItem> ourofferlist ;
    OfferItem oneOffering;
    MyAdapter myAdapter;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    EditText et_searchoffer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recylerview_home= (RecyclerView)findViewById(R.id.recylerview_home);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(Home.this,1);
        recylerview_home.setLayoutManager(gridLayoutManager);
        et_searchoffer=(EditText)findViewById(R.id.et_searchoffer);


        ourofferlist = new ArrayList<>();
        //oneOffering = new OfferItem("Wadduwa" /*R.drawable.downloadfood*/);
        //ourofferlist.add(oneOffering);
        //oneOffering = new OfferItem(/*R.drawable.downloadfoods*/);
        //ourofferlist.add(oneOffering);

        myAdapter = new MyAdapter(Home.this,ourofferlist);
        recylerview_home.setAdapter(myAdapter);

        databaseReference = FirebaseDatabase.getInstance("https://wasteless-88bc9-default-rtdb.firebaseio.com/").getReference("OfferItem");


        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ourofferlist.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()){
                    OfferItem item = itemSnapshot.getValue(OfferItem.class);
                    ourofferlist.add(item);
                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        et_searchoffer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filtering(editable.toString());
            }
        });

    }

    private void filtering(String text){
        ArrayList <OfferItem> filterList = new ArrayList<>();
        for (OfferItem item:ourofferlist){
            if(item.getOfferName().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);

            }
        }
        myAdapter.filteredList(filterList);

    }
    public void btn_addofferActivity(View view) {

        startActivity(new Intent(this,OfferDetailActivity.class));
    }
}