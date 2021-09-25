package com.example.wasteless;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUserActivity extends AppCompatActivity {

    EditText txt_name, txt_address, txt_phoneno, txt_email, txt_password;
    Button Regbtn;
    TextView already_user;


    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    FirebaseAuth firebaseAuth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        txt_name = findViewById(R.id.name_reg);
        txt_address = findViewById(R.id.address_reg);
        txt_phoneno = findViewById(R.id.phone_reg);
        txt_email = findViewById(R.id.email_reg);
        txt_password = findViewById(R.id.password_reg);
        Regbtn = findViewById(R.id.reg_btn);

        already_user = findViewById(R.id.reg_textview);

        databaseReference = FirebaseDatabase.getInstance().getReference("UserModel");

        firebaseAuth = FirebaseAuth.getInstance();



        Regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = txt_name.getText().toString();
                String address = txt_address.getText().toString();
                String phoneno = txt_phoneno.getText().toString();
                String email = txt_email.getText().toString();
                String password = txt_password.getText().toString();


                if(TextUtils.isEmpty(name)){
                    Toast.makeText(RegisterUserActivity.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                }

                if(TextUtils.isEmpty(address)){
                    Toast.makeText(RegisterUserActivity.this, "Please Enter Address", Toast.LENGTH_SHORT).show();
                }

                if(TextUtils.isEmpty(phoneno)){
                    Toast.makeText(RegisterUserActivity.this, "Please Enter Phone No", Toast.LENGTH_SHORT).show();
                }

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(RegisterUserActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterUserActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }


                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterUserActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    UserModel information = new UserModel(
                                            name,
                                            address,
                                            phoneno,
                                            email
                                    );

                                    FirebaseDatabase.getInstance().getReference("UserModel")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(RegisterUserActivity.this, "Register Successful", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), Home.class));
                                        }
                                    });




                                }else {



                                }
                            }
                        });



            }
        });




        already_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterUserActivity.this, MainActivity.class ));
            }
        });
    }
}