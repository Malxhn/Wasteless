package com.example.wasteless;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class NewOfferActivity extends AppCompatActivity {

    private EditText mOfferName_editTxt;
    private EditText mAddress_editTxt;
    private EditText mPickUpby_editTxt;
    private EditText mPickUpfrom_editTxt;
    private EditText mdescription_editTxt;
    private EditText mservings_editTxt;
    private Spinner moffer_categories_spinner;

    private Button mAdd_btn;
    private Button mBack_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_offer);

        mOfferName_editTxt=(EditText) findViewById(R.id.offerrname_editTxt);
        mAddress_editTxt=(EditText) findViewById(R.id.address_editTxt);
        mPickUpby_editTxt=(EditText) findViewById(R.id.pickupby_editTxt);
        mPickUpfrom_editTxt=(EditText) findViewById(R.id.pickupfrom_editTxt);
        mdescription_editTxt=(EditText) findViewById(R.id.description_editTxt);
        mservings_editTxt=(EditText) findViewById(R.id.servings_editTxt);
        mAdd_btn=(Button) findViewById(R.id.add_btn);
        mBack_btn=(Button) findViewById(R.id.back_btn);
        moffer_categories_spinner=(Spinner) findViewById(R.id.offer_categories_spinner);


        mAdd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OfferItem offeritem=new OfferItem();
                offeritem.setOfferName(mOfferName_editTxt.getText().toString());
                offeritem.setAddress(mAddress_editTxt.getText().toString());
                offeritem.setPickUp_By(mPickUpby_editTxt.getText().toString());
                offeritem.setPickUp_From(mPickUpfrom_editTxt.getText().toString());
                offeritem.setDescription(mdescription_editTxt.getText().toString());
                offeritem.setServings(mservings_editTxt.getText().toString());
                offeritem.setCategory(moffer_categories_spinner.getSelectedItem().toString());

                new FirebaseDatabaseHelper().addOfferItem(offeritem, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<OfferItem> offeritems, List<String> keys) {


                    }

                    @Override
                    public void DataIsInserted() {
                        Toast.makeText(NewOfferActivity.this, "Offer Added to the database", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });

            }
        });
        mBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();return;
            }
        });
    }
}