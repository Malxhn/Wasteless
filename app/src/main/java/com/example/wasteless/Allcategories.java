package com.example.wasteless;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Allcategories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allcategories);
    }

    public void openuseraccount(View view) {
        Intent intent = new Intent (this,UserAccount.class);
        startActivity(intent);

    }
}