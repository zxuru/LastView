package com.lastview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Pedidos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        getSupportActionBar().hide();
    }
}