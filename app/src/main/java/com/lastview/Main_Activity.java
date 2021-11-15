package com.lastview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lastview.database.AdminSQLiteOpenHelper;

public class Main_Activity extends AppCompatActivity {

    private EditText nUsuario, pUsuario;
    private TextView tvError;
    private Button btnReg, btnLog;
    private ProgressBar barra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        nUsuario = (EditText) findViewById(R.id.ptUser);
        pUsuario = (EditText) findViewById(R.id.passUser);

        btnReg = (Button) findViewById(R.id.btnRegistro);
        btnLog = (Button) findViewById(R.id.btnLogIn);

        barra = (ProgressBar) findViewById(R.id.pbar);

        barra.setVisibility(View.INVISIBLE);

        tvError = (TextView) findViewById(R.id.tV6);

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Task().execute();
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Tarea().execute();
            }
        });
    }

    /*
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
     */

    public void clean(){
        nUsuario.setText("");
        pUsuario.setText("");
    }

    class Tarea extends AsyncTask<String, Void, String> {
        Intent i = new Intent(getBaseContext(), Home_ACT.class);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            barra.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String a = "true";
            if (1+1 == 2)
                a = "true";

            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getBaseContext(), "lastview", null, 1 );
            SQLiteDatabase db = admin.getWritableDatabase();

            String pass = pUsuario.getText().toString();
            String nom = nUsuario.getText().toString();

            try {
                if (!pass.isEmpty() && !nom.isEmpty()) {
                    try {
                        Cursor file = db.rawQuery("SELECT ID FROM usuarios WHERE username = '" + nom + "' AND password = '" + pass + "';", null);
                            if (file.getCount() == 0) {
                                db.execSQL("INSERT INTO usuarios (ID, username, password) VALUES (null, '"+ nom +"', '" + pass + "');");
                                for (int i = 0; i <= 10; i++) {
                                    Thread.sleep(100);
                                }
                                clean();
                                return "Creacion Exitosa";
                            }
                    } catch (Exception e) {
                        clean();
                        return "Nombre o Contraseña erronea";
                    }
                } else {
                    throw new Exception("El nombre y contraseña no pueden estar vacios");
                }
            } catch (Exception e) {
                clean();
                return "El nombre y contraseña no pueden estar vacios";
            }
            return a;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            barra.setVisibility(View.INVISIBLE);
            if (s.equalsIgnoreCase("Nombre o Contraseña erronea")) {
                tvError.setText("Nombre o Contraseña erronea");
                Toast.makeText(getBaseContext(), "Nombre o Contraseña erronea", Toast.LENGTH_SHORT).show();
            } else if (s.equalsIgnoreCase("El nombre y contraseña no pueden estar vacios")) {
                tvError.setText("El nombre y contraseña no pueden estar vacios");
                Toast.makeText(getBaseContext(), "El nombre y contraseña no pueden estar vacios", Toast.LENGTH_SHORT).show();
            } else if (s.equalsIgnoreCase("Creacion Exitosa")) {
                tvError.setText("Usuario Creado Exitosamente");
            } else {
                Toast.makeText(getBaseContext(), "Usuario Creado Exitosamente", Toast.LENGTH_SHORT).show();
            }
            clean();
        }
    }

    class Task extends AsyncTask<String, Void, String> {
        Intent i = new Intent(getBaseContext(), Home_ACT.class);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            barra.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String a = "true";
            if (1+1 == 2)
                a = "true";

            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getBaseContext(), "lastview", null, 1 );
            SQLiteDatabase db = admin.getWritableDatabase();

            String pass = pUsuario.getText().toString();
            String nom = nUsuario.getText().toString();

            try {
                if (!pass.isEmpty() && !nom.isEmpty()) {
                    try {
                        Cursor file = db.rawQuery("SELECT ID, username FROM usuarios WHERE username = '" + nom + "' AND password = '" + pass + "';", null);
                        if (file.moveToFirst()) {
                            i.putExtra("name", file.getString(1));
                            i.putExtra("id", file.getString(0));
                            for (int i = 0; i<=10; i++) {
                                Thread.sleep(100);
                            }
                        } else {
                            throw new Exception("Nombre o Contraseña erronea");
                        }
                    } catch (Exception e) {
                        clean();
                        return "Nombre o Contraseña erronea";
                    }
                } else {
                    throw new Exception("El nombre y contraseña no pueden estar vacios");
                }
            } catch (Exception e) {
                clean();
                return "El nombre y contraseña no pueden estar vacios";
            }
            return a;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            barra.setVisibility(View.INVISIBLE);
            if (s.equalsIgnoreCase("Nombre o Contraseña erronea")) {
                tvError.setText("Nombre o Contraseña erronea");
                Toast.makeText(getBaseContext(), "Nombre o Contraseña erronea", Toast.LENGTH_SHORT).show();
            } else if (s.equalsIgnoreCase("El nombre y contraseña no pueden estar vacios")) {
                tvError.setText("El nombre y contraseña no pueden estar vacios");
                Toast.makeText(getBaseContext(), "El nombre y contraseña no pueden estar vacios", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(i);
            }
            clean();
        }
    }
/*
    public void logIn(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "lastview", null, 1 );
        SQLiteDatabase db = admin.getWritableDatabase();

        String pass = pUsuario.getText().toString();
        String nom = nUsuario.getText().toString();

        if (!pass.isEmpty() && !nom.isEmpty()){
            Cursor file = db.rawQuery("SELECT ID, username FROM usuarios WHERE username = '" + nom + "' AND password = '" + pass + "';", null);
            if (file.moveToFirst()){

                Intent i = new Intent(getBaseContext(), Home_ACT.class);
                i.putExtra("name", nom);
                i.putExtra("id", file.getString(1));
                startActivity(i);
            } else {
                Toast.makeText(this, "Nombre o Contraseña erronea", Toast.LENGTH_SHORT).show();
                clean();
            }
        } else {
            Toast.makeText(this, "El nombre y contraseña no pueden estar vacios", Toast.LENGTH_SHORT).show();
            clean();
        }
    }

 */

}