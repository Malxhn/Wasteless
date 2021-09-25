package com.example.wasteless;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class UserAccount extends AppCompatActivity {

    private Button logoutBtn, btn_Home;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);


        logoutBtn = findViewById(R.id.logout_btn);
        btn_Home = findViewById(R.id.btnHome);

        mAuth = FirebaseAuth.getInstance();


        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(UserAccount.this, MainActivity.class));
                finish();
            }
        });

        btn_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserAccount.this, Home.class));
            }
        });

    }
}