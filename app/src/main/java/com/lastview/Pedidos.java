package com.lastview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lastview.Objetos.Products;
import com.lastview.database.AdminSQLiteOpenHelper;

public class Pedidos extends AppCompatActivity {

    private TextView textPedidos, pedido, info;
    private EditText cantidad;
    private Button volver, btnpedir;
    private Spinner spinner;

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
        spinner = (Spinner) findViewById(R.id.spinner);
        btnpedir = (Button) findViewById(R.id.btnPedir);
        cantidad = (EditText) findViewById(R.id.edNumber);


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

        pedido.setText("");

        textPedidos.setText("Pedido");

        info.setText("Vizualiza tus pedidos realizados");

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getBaseContext(), "lastview", null, 1 );
        SQLiteDatabase db = admin.getWritableDatabase();

        Products oProducts = new Products();

        String[] lProducts = oProducts.getNombre();

        ArrayAdapter adapterProds = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lProducts);

        spinner.setAdapter(adapterProds);

        btnpedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cantidad.getText();
                if ((!cantidad.getText().equals(0) || !cantidad.getText().equals(""))) {
                    String element = spinner.getSelectedItem().toString();
                    int cant = Integer.parseInt(cantidad.getText().toString());
                    db.execSQL("INSERT INTO pedidos (ID, productos, cant, cliente) VALUES (null, '" + element + "'," + cant + "," + id + ")");
                    Toast.makeText(getBaseContext(), "Pedido registrado", Toast.LENGTH_SHORT).show();
                    clean();
                }
            }
        });

    }

    public void load(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getBaseContext(), "lastview", null, 1 );
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor file = db.rawQuery("SELECT productos, cant FROM pedidos WHERE cliente = " + id + ";", null);
        if ((file.moveToFirst() && file.getCount() != 0)) {
            pedido.setText("Producto Solicitados:\n");
            int i = 0;
            int c = 0;
            while (i < file.getColumnCount()){
                pedido.append(file.getString(0)+" ");
                pedido.append(file.getString(1)+" ");
                pedido.append("\n");
                file.moveToNext();
                c++;
                if (c== file.getCount()){
                    i=2;
                }
            }
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

    public void clean(){
        cantidad.setText("");
    }
}