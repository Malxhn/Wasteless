package com.example.wasteless;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileDetails extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private String userID;
    String fullname;
    String fulladdress;
    String fullphone;
    String fullemail;
    TextView tv_myname;
    TextView tv_myaddress;
    TextView tv_myphone;
    TextView tv_myemail;
    TextView tv_name;
    Button letsDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);
        tv_myname=(TextView)findViewById(R.id.et_myname);
        tv_myaddress=(TextView)findViewById(R.id.et_myaddress);
        tv_myphone=(TextView)findViewById(R.id.et_myphone);
        tv_myemail=(TextView)findViewById(R.id.et_mymail);
        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("UserModel");
        userID=user.getUid();
        tv_name=(TextView)findViewById(R.id.tv_name);
        letsDelete=(Button)findViewById(R.id.btn_letsdelete);


        letsDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ProfileDetails.this);
                dialog.setTitle("Are you sure?");
                dialog.setMessage("Deleting this account will result in completely removing your account from the system and you won't be able to access the app");
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(ProfileDetails.this, "Account Deleted", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ProfileDetails.this,MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(ProfileDetails.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                dialog.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });



        databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel userprofile = snapshot.getValue(UserModel.class);
                if(userprofile != null){
                    fullname = userprofile.name;
                    fulladdress = userprofile.address;
                    fullphone = userprofile.phoneno;
                    fullemail = userprofile.email;

                    tv_myname.setText(fullname);
                    tv_myaddress.setText(fulladdress);
                    tv_myphone.setText(fullphone);
                    tv_myemail.setText(fullemail);
                    tv_name.setText(fullname);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


    }
    public void openupdate(View view){
        Intent intent = new Intent (this,UserProfileActivity.class);
        startActivity(intent);
    }

    public void openuseraccount(View view){

    }

}