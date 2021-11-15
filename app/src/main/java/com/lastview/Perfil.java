package com.lastview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lastview.database.AdminSQLiteOpenHelper;

public class Perfil extends AppCompatActivity {

    private TextView bUser;
    private EditText nUsua, passUsua;
    private Button uPass, uNusu, dAcc;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        getSupportActionBar().hide();

        bUser = (TextView) findViewById(R.id.txtPerfil);
        nUsua = (EditText) findViewById(R.id.txtUser);
        passUsua = (EditText) findViewById(R.id.txtPassword);
        uPass = (Button) findViewById(R.id.btnUpdPass);
        uNusu = (Button) findViewById(R.id.btnUpdUsName);
        dAcc = (Button) findViewById(R.id.btnDeleteAccount);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getBaseContext(), "lastview", null, 1 );
        SQLiteDatabase db = admin.getWritableDatabase();

        final String newString;
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

        setNumber(newString);

        Cursor file = db.rawQuery("SELECT username FROM usuarios WHERE id = " + newString +";", null);
        if (file.moveToFirst()) {
            bUser.setText("Bienvenido " + file.getString(0));
        }
    }

    public void updNomUsu (View view) {
        try {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getBaseContext(), "lastview", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();

            String user = nUsua.getText().toString();
            String pass = passUsua.getText().toString();

            Cursor file = db.rawQuery("SELECT username, password FROM usuarios WHERE id = " + id + ";", null);
            if (file.moveToFirst()) {
                String passSql = file.getString(1);
                if (passSql.equals(pass)) {
                    db.execSQL("UPDATE usuarios SET username= '" + user + "' WHERE id = " + id + ";");
                    clean();
                    Toast.makeText(this, "Nombre de usuario Actualizado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Error upd nombre", Toast.LENGTH_SHORT).show();
                    clean();
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Fallo total", Toast.LENGTH_SHORT).show();
        }
    }

    public void updPassUsu (View view) {
        try {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getBaseContext(), "lastview", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();

            String user = nUsua.getText().toString();
            String pass = passUsua.getText().toString();

            Cursor file = db.rawQuery("SELECT username, password FROM usuarios WHERE id = " + id + ";", null);
            if (file.moveToFirst()) {
                String userSql = file.getString(0);
                if (userSql.equals(user)) {
                    db.execSQL("UPDATE usuarios SET password = '" + pass + "' WHERE id = " + id + ";");
                    clean();
                    Toast.makeText(this, "Contraseña de usuario Actualizada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Error upd pass", Toast.LENGTH_SHORT).show();
                    clean();
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Fallo total", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteUser (View view) {
        try {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getBaseContext(), "lastview", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();

            String user = nUsua.getText().toString();
            String pass = passUsua.getText().toString();

            Cursor file = db.rawQuery("SELECT username, password FROM usuarios WHERE id = " + id + ";", null);

            if (file.moveToFirst()) {
                String userSql = file.getString(0);
                String passSql = file.getString(1);
                if ((userSql.equals(user)) && (passSql.equals(pass))) {
                    db.execSQL("DELETE FROM usuarios WHERE id = " + id + " AND username = '" + user + "' AND password = '" + pass + "';");
                    Toast.makeText(this, "Cuenta Eliminada", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this, Main_Activity.class);
                    clean();
                    startActivity(i);
                } else {
                    clean();
                    Toast.makeText(this, "Datos erroneos", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Fallo total", Toast.LENGTH_SHORT).show();
        }
    }

    public void clean(){
        nUsua.setText("");
        passUsua.setText("");
    }

    public void setNumber(String number) {
        this.id = number;
    }
}