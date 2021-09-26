package com.example.wasteless;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class OfferItemlistActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_item);
        mRecyclerView=(RecyclerView) findViewById(R.id.recyclerview_offeritems);
        new FirebaseDatabaseHelper().readOfferItems(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<OfferItem> offeritems, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView,OfferItemlistActivity.this,offeritems,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.offeritemlist_activity_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.new_offer:
                startActivity(new Intent(this,NewOfferActivity.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}