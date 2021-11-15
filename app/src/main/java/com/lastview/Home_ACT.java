package com.lastview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class Home_ACT extends AppCompatActivity {

    private VideoView video;

    private String newString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        video = findViewById(R.id.videoView);

        String ruta = "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(ruta);
        video.setVideoURI(uri);

        video.start();

        MediaController media = new MediaController(this);
        video.setMediaController(media);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("id");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("id");
        }
    }

    public void info(View view) {
        Intent i = new Intent(this, Info_Act.class);
        startActivity(i);
    }

    public void insumos(View view) {
        Intent i = new Intent(this, Insumos_Act.class);
        startActivity(i);
    }

    public void perfil(View view) {
        Intent i = new Intent(this, Perfil.class);
        i.putExtra("id", newString);
        startActivity(i);
    }

    public void pedidos(View view) {
        Intent i = new Intent(this, Pedidos.class);
        i.putExtra("id", newString);
        startActivity(i);
    }
}