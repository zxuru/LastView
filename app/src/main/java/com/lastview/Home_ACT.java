package com.lastview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Home_ACT extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("name");
        }
    }
}