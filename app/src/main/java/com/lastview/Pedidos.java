package com.lastview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lastview.database.AdminSQLiteOpenHelper;

public class Pedidos extends AppCompatActivity {

    private TextView textPedidos, pedido, info;
    private Button volver;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        getSupportActionBar().hide();

        textPedidos = (TextView) findViewById(R.id.tvPedidos);
        pedido = (TextView) findViewById(R.id.txtPedidos);
        info = (TextView) findViewById(R.id.tvInfo);
        volver = (Button) findViewById(R.id.btnVolverPedidos);

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

        textPedidos.setText("Pedido");

        info.setText("Vizualiza tus pedidos realizados");

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getBaseContext(), "lastview", null, 1 );
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor file = db.rawQuery("SELECT productos FROM pedidos WHERE cliente = '" + newString + "';", null);
        if (file.moveToFirst()) {
            pedido.setText("Producto Solicitados:\n" + file.getString(0));
        }

    }

    public void Volver(View view){
        Intent i = new Intent(this, Home_ACT.class);
        i.putExtra("id", id);
        startActivity(i);
    }

    public void setNumber(String number) {
        this.id = number;
    }
}