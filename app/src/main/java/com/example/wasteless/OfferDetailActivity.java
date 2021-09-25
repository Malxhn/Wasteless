package com.example.wasteless;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class OfferDetailActivity extends AppCompatActivity {

    private Context mContext;



    TextView txt_detaildes;
    TextView txt_detailaddress;
    TextView txt_detailcategory;
    TextView txt_detailoffername;
    TextView txt_detailpby;
    TextView txt_detailpfrom;
    TextView txt_detailservings;
    ImageView img_detailimage;

    EditText et_reqOfferName, et_reqOfferEmail, et_reqOfferPhone;
    Button btn_reqOffer;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    DataSnapshot dataSnapshot;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_detail);

        txt_detaildes = (TextView) findViewById(R.id.txt_detaildes);
        txt_detailaddress =(TextView) findViewById(R.id.txt_detailaddress);
        txt_detailcategory = (TextView) findViewById(R.id.txt_detailcategory);
        txt_detailoffername = (TextView) findViewById(R.id.txt_detailoffername);
        txt_detailpby = (TextView) findViewById(R.id.txt_detailpby);
        txt_detailpfrom = (TextView) findViewById(R.id.txt_detailpfrom);
        txt_detailservings = (TextView) findViewById(R.id.txt_detailservings);
        img_detailimage=(ImageView) findViewById(R.id.img_detailimage);

        et_reqOfferName = (EditText) findViewById(R.id.editTextReqOfferName);
        et_reqOfferEmail = (EditText) findViewById(R.id.editTextReqOfferEmail);
        et_reqOfferPhone = (EditText) findViewById(R.id.editTextReqOfferPhone);
        btn_reqOffer = (Button) findViewById(R.id.btnReqOffer);




        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null){
            txt_detaildes.setText(mBundle.getString("Description"));
            txt_detailaddress.setText(mBundle.getString("Address"));
            txt_detailcategory.setText(mBundle.getString("Category"));
            txt_detailoffername.setText(mBundle.getString("OfferName"));
            txt_detailpby.setText(mBundle.getString("PickUp_By"));
            txt_detailpfrom.setText(mBundle.getString("PickUp_From"));
            txt_detailservings.setText(mBundle.getString("Servings"));

            databaseReference = FirebaseDatabase.getInstance().getReference()
                    .child("OfferItem");





            //img_detailimage.setImageResource(mBundle.getInt("Image"));

            //Glide.with(this)
            // .load(mBundle.getString("Image"))
            //.into(img_detailimage);


        }

        btn_reqOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String description = txt_detaildes.getText().toString();
                String address = txt_detailaddress.getText().toString();
                String category = txt_detailcategory.getText().toString();
                String offername = txt_detailoffername.getText().toString();
                String pickupby = txt_detailpby.getText().toString();
                String pickupfrom = txt_detailpfrom.getText().toString();
                String servings = txt_detailservings.getText().toString();

                String reqoffername = et_reqOfferName.getText().toString();
                String reqofferemail = et_reqOfferEmail.getText().toString();
                String reqofferphone = et_reqOfferPhone.getText().toString();



                //String key = databaseReference.child().getKey();


                //String key = dataSnapshot.getChildren().iterator().next().getKey();


                OfferItem offeritem = new OfferItem();
                offeritem.setDescription(description);
                offeritem.setAddress(address);
                offeritem.setCategory(category);
                offeritem.setOfferName(offername);
                offeritem.setPickUp_By(pickupby);
                offeritem.setPickUp_From(pickupfrom);
                offeritem.setServings(servings);
                offeritem.setReqOfferName(reqoffername);
                offeritem.setReqOfferEmail(reqofferemail);
                offeritem.setReqOfferPhone(reqofferphone);




                databaseReference.child("food1").setValue(offeritem).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(OfferDetailActivity.this, "Update Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(OfferDetailActivity.this, Home.class ));


                    }
                });



            }






        });






    }
}