package com.example.wasteless;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class OfferItemDetailsActivity extends AppCompatActivity {

    private EditText mOfferName_editTxt;
    private EditText mAddress_editTxt;
    private EditText mDescription_editTxt;
    private EditText mPickUpBy_editTxt;
    private EditText mPickUpFrom_editTxt;
    private EditText mServings_editTxt;
    private Spinner mOfferItem_category_spinner;

    private Button mUpdate_btn;
    private Button mDelete_btn;
    private Button mBack_btn;

    private String key;
    private String offername;
    private String address;
    private String description;
    private String pickupby;
    private String pickupfrom;
    private  String servings;
    private String  category;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_item_details);

        key =getIntent().getStringExtra("key");
        offername=getIntent().getStringExtra("offername");
        address=getIntent().getStringExtra("address");
        description=getIntent().getStringExtra("description");
        pickupby=getIntent().getStringExtra("pickupby");
        pickupfrom=getIntent().getStringExtra("pickupfrom");
        servings=getIntent().getStringExtra("servings");
        category=getIntent().getStringExtra("category");


        mOfferName_editTxt=(EditText) findViewById(R.id.offerrname_editTxt);
        mOfferName_editTxt.setText(offername);
        mAddress_editTxt=(EditText) findViewById(R.id.address_editTxt);
        mAddress_editTxt.setText(address);
        mDescription_editTxt=(EditText) findViewById(R.id.description_editTxt);
        mDescription_editTxt.setText(description);
        mPickUpBy_editTxt=(EditText) findViewById(R.id.pickupby_editTxt);
        mPickUpBy_editTxt.setText(pickupby);
        mPickUpFrom_editTxt=(EditText) findViewById(R.id.pickupfrom_editTxt);
        mPickUpFrom_editTxt.setText(pickupfrom);
        mServings_editTxt=(EditText) findViewById(R.id.servings_editTxt);
        mServings_editTxt.setText(servings);
        mOfferItem_category_spinner=(Spinner) findViewById(R.id.offer_categories_spinner);
        mOfferItem_category_spinner.setSelection(getIndex_SpinnerItem(mOfferItem_category_spinner,category));

        mUpdate_btn=(Button) findViewById(R.id.update_btn);
        mDelete_btn=(Button) findViewById(R.id.delete_btn);
        mBack_btn=(Button) findViewById(R.id.back_btn);

        mUpdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OfferItem offeritem=new OfferItem();
                offeritem.setOfferName(mOfferName_editTxt.getText().toString());
                offeritem.setAddress(mAddress_editTxt.getText().toString());
                offeritem.setDescription(mDescription_editTxt.getText().toString());
                offeritem.setPickUp_By(mPickUpBy_editTxt.getText().toString());
                offeritem.setPickUp_From(mPickUpFrom_editTxt.getText().toString());
                offeritem.setServings(mServings_editTxt.getText().toString());
                offeritem.setCategory(mOfferItem_category_spinner.getSelectedItem().toString());

                new FirebaseDatabaseHelper().updateOfferItem(key, offeritem, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<OfferItem> offeritems, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(OfferItemDetailsActivity.this, "Offer item updated successfully", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });



            }
        });

        mDelete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FirebaseDatabaseHelper().deleteOfferItem(key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<OfferItem> offeritems, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(OfferItemDetailsActivity.this, "Offer Item Deleted", Toast.LENGTH_SHORT).show();
                        finish();
                        return;

                    }
                });

            }
        });

        mBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                return;
            }
        });

    }
    private int getIndex_SpinnerItem(Spinner spinner,String item)
    {
        int index=0;
        for(int i=0;i<spinner.getCount();i++)
        {
            if(spinner.getItemAtPosition(i).equals(item))
            {
                index =i;
                break;

            }
        }
        return index;
    }
}