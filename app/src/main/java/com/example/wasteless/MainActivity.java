package com.example.wasteless;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText mEmail, mPass;
    private Button loginBtn;

    private FirebaseAuth mAuth;

    TextView not_registered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail = findViewById(R.id.email_login);
        mPass = findViewById(R.id.password_login);
        loginBtn = findViewById(R.id.login_btn);

        mAuth = FirebaseAuth.getInstance();


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });





        not_registered = findViewById(R.id.login_textview);
        not_registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterUserActivity.class ));
            }
        });
    }


    private  void loginUser() {
        String email = mEmail.getText().toString();
        String pass = mPass.getText().toString();

        if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            if(!pass.isEmpty()){
                mAuth.signInWithEmailAndPassword(email, pass)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, Home.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Login Fail", Toast.LENGTH_SHORT).show();
                    }
                });

            }else {
                mPass.setError("Empty Fields");
            }
        }else if(email.isEmpty()){
            mEmail.setError("Empty Fields");
        }else {
            mEmail.setError("Please Enter Correct Email");
        }

    }


}