package com.lastview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main_Activity extends AppCompatActivity {

    private EditText nUsuario, pUsuario;
    private Button btnOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios",null,1);

        nUsuario = (EditText) findViewById(R.id.ptUser);
        pUsuario = (EditText) findViewById(R.id.passUser);

        btnOut = (Button) findViewById(R.id.btnSalir);
    }

    public Integer checkUser()
    {
        int x;

        if (nUsuario.getText().toString().equals("Hugo") &&
                pUsuario.getText().toString().equals("123"))
        {
            x = 0;
        }
        else if (nUsuario.getText().toString().equals("") &&
                pUsuario.getText().toString().equals(""))
        {
            Toast.makeText(this, "Campos vacios", Toast.LENGTH_SHORT).show();
            x = 1;
        }
        else
        {
            Toast.makeText(this, "Datos Incorrectos", Toast.LENGTH_SHORT).show();
            x = 1;
        }
        return x;
    }

    public void clean(){
        nUsuario.setText("");
        pUsuario.setText("");
    }

    public void logIn(View view)
    {
        if ( checkUser() == 0 )
        {
            Intent i = new Intent(this, Home_ACT.class);
            startActivity(i);
            clean();
        } else {
            clean();
        }
    }

}