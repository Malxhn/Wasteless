package com.example.wasteless;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfileActivity extends AppCompatActivity {
    EditText et_editname;
    EditText et_editaddress;
    EditText et_phone;
    TextView tvx_email;
    TextView tv_name;
    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private String userID;
    String fullname;
    String fulladdress;
    String fullphone;
    String fullemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        //SessionManagement sessionManagement=new SessionManagement(this);
        // HashMap <String,String> userdetails = sessionManagement.getUserDetailsfromSession();

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("UserModel");
        userID=user.getUid();

        et_editname = (EditText) findViewById(R.id.et_editname);
        et_editaddress = (EditText) findViewById(R.id.et_editaddress);
        et_phone = (EditText) findViewById(R.id.et_editphone);
        tvx_email = (TextView) findViewById(R.id.tvx_editemail);
        tv_name=(TextView)findViewById(R.id.tv_name);
        databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel userprofile = snapshot.getValue(UserModel.class);
                if(userprofile != null){
                    fullname = userprofile.name;
                    fulladdress = userprofile.address;
                    fullphone = userprofile.phoneno;
                    fullemail = userprofile.email;

                    et_editname.setText(fullname);
                    et_editaddress.setText(fulladdress);
                    et_phone.setText(fullphone);
                    tvx_email.setText(fullemail);
                    tv_name.setText(fullname);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserProfileActivity.this, "Something wrong happend!", Toast.LENGTH_SHORT).show();
            }

        });





    }

    public void updatemyprofile(View view) {
        if(isNameChanged() || isAddressChanged()|| isPhoneChnaged()){
            Toast.makeText(this, "User profile updated", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isPhoneChnaged() {
        if(!fullphone.equals(et_phone.getText().toString())){
            databaseReference.child(userID).child("phoneno").setValue(et_phone.getText().toString());
            fullphone=et_phone.getText().toString();
            return true;
        }else{
            return false;
        }
    }

    private boolean isAddressChanged() {
        if(!fulladdress.equals(et_editaddress.getText().toString())){
            databaseReference.child(userID).child("address").setValue(et_editaddress.getText().toString());
            fulladdress=et_editaddress.getText().toString();
            return true;
        }else{
            return false;
        }
    }

    private boolean isNameChanged() {
        if(!fullname.equals(et_editname.getText().toString())){
            databaseReference.child(userID).child("name").setValue(et_editname.getText().toString());
            fullname = et_editname.getText().toString();
            return true;
        }else{
            return false;
        }
    }
    public void openuseraccount(View view){

    }
}