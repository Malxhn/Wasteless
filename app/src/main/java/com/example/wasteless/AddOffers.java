package com.example.wasteless;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class AddOffers extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST=1;
    private Button mButtonChooseImage;

    private ImageView mImageView;
    private ProgressBar mProgressBar;

    private Uri mImageUri;

    private StorageReference mStorageRef;
    DatabaseReference dbRef;
    private StorageTask mUploadTask;

    EditText etoffername,etservings,etaddress,etpFrom,etpBy,etdesc;
    Spinner spcategory;
    Button btnaddoffer;
    OfferItem offobj;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addoffers);

        mButtonChooseImage=findViewById(R.id.btnChooseimage);

        mImageView=findViewById(R.id.image_view);
        mProgressBar=findViewById(R.id.progress_bar);

        mStorageRef= FirebaseStorage.getInstance().getReference("uploads");
        dbRef= FirebaseDatabase.getInstance("https://wasteless-88bc9-default-rtdb.firebaseio.com/").getReference().child("OfferItem");


        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();

            }
        });

       /* mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

        etoffername=findViewById(R.id.offernameET);
        etservings=findViewById(R.id.servingsET);
        etaddress=findViewById(R.id.addressET);
        etpFrom=findViewById(R.id.pFromET);
        etpBy=findViewById(R.id.pByET);
        etdesc=findViewById(R.id.descET);
        spcategory=(Spinner)findViewById(R.id.spinnerCategories);

        btnaddoffer=findViewById(R.id.buttonAddOffer);

        offobj=new OfferItem();

    }
    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode== RESULT_OK
                && data != null && data.getData() != null){
            mImageUri=data.getData();
            //Picasso.get(this).load(mImageUri).into(mImageView);
            mImageView.setImageURI(mImageUri);
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver cR= getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));

    }

    private void uploadFile(){
        if (mImageUri!=null){
            StorageReference fileReference=mStorageRef.child(System.currentTimeMillis()
                    +"."+getFileExtension(mImageUri));

            mUploadTask=fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler=new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            },500);
                            Toast.makeText(AddOffers.this, "Image upload successful", Toast.LENGTH_LONG).show();

                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful());
                            Uri downloadUrl = urlTask.getResult();
                            offobj.setmImageUrl(downloadUrl.toString());
                            String uploadId=dbRef.push().getKey();
                            dbRef.child(uploadId).setValue(offobj);


                           /*offobj.setmImageUrl(taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());

                            String uploadId=dbRef.push().getKey();
                            dbRef.child(uploadId).setValue(offobj);*/
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddOffers.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double progress=(100.0 * snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                            mProgressBar.setProgress((int)progress);
                        }
                    });

        }else{
            Toast.makeText(this,"No file Selected",Toast.LENGTH_SHORT).show();
        }
    }


    public void ClearControls(){
        etoffername.setText("");
        etservings.setText("");
        etaddress.setText("");
        etpFrom.setText("");
        etpBy.setText("");
        etdesc.setText("");
    }
    public void CreateOffer(View view){

        try {
            if(TextUtils.isEmpty(etoffername.getText().toString()))
                Toast.makeText(getApplicationContext(),"Please enter the offer name",Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(etaddress.getText().toString()))
                Toast.makeText(getApplicationContext(),"Please enter the address",Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(etpFrom.getText().toString()))
                Toast.makeText(getApplicationContext(),"Please enter the time and date to pickup",Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(etpBy.getText().toString()))
                Toast.makeText(getApplicationContext(),"Please enter the time and date pickup ends",Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(etdesc.getText().toString()))
                Toast.makeText(getApplicationContext(),"Please enter offer description",Toast.LENGTH_SHORT).show();
            else{

                //take inputs from the user and assigning them to the offer object
                offobj.setOfferName(etoffername.getText().toString().trim());
                offobj.setServings(etservings.getText().toString().trim());
                offobj.setAddress(etaddress.getText().toString().trim());
                offobj.setPickUp_From(etpFrom.getText().toString().trim());
                offobj.setPickUp_By(etpBy.getText().toString().trim());
                offobj.setDescription(etdesc.getText().toString().trim());
                offobj.setCategory(spcategory.getSelectedItem().toString());

                if (mUploadTask!=null && mUploadTask.isInProgress()){
                    Toast.makeText(AddOffers.this, "upload in progress ", Toast.LENGTH_SHORT).show();
                }else{ uploadFile();}
                //insert into the database

                //dbRef.push().setValue(offobj);


                //if success feedback to user via toast
                Toast.makeText(getApplicationContext(),"Offer Added, Thank you ",Toast.LENGTH_SHORT).show();
                ClearControls();

            }

        }catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(),"Enter a number in servings",Toast.LENGTH_SHORT).show();

        }

    }
}