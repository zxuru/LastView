package com.lastview;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lastview.Objetos.Products;
import com.lastview.database.AdminSQLiteOpenHelper;

import org.w3c.dom.Text;

import java.lang.reflect.Array;

public class Insumos_Act extends AppCompatActivity {
    private Spinner spin;
    private RatingBar rtbar;
    private TextView insumo, infoIns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insumos);
        getSupportActionBar().hide();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getBaseContext(), "lastview", null, 1 );
        SQLiteDatabase db = admin.getWritableDatabase();

        rtbar = (RatingBar) findViewById(R.id.ratingBar);
        insumo = (TextView) findViewById(R.id.insTv);
        infoIns = (TextView) findViewById(R.id.tvInsInfo);
        spin = (Spinner) findViewById(R.id.spnProds);

        Products oProducts = new Products();

        String[] lProducts = oProducts.getNombre();

        ArrayAdapter adapterProds = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lProducts);

        spin.setAdapter(adapterProds);

        infoIns.setText("Bienvenido a la seccion en la cual puedes vizualizar la cantidad total " +
                "de productos existentes su nombre y precio");

        rtbar.setNumStars(5);
        rtbar.setRating(0);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                insumo.setText(" ");
                String nom = spin.getSelectedItem().toString();
                Cursor file = db.rawQuery("SELECT cant, precio FROM productos WHERE nombre = '" + nom + "';", null);
                if (file.moveToFirst()) {
                    insumo.setText("Producto: " +  spin.getSelectedItem().toString() +
                            "\nPrecio: " + file.getString(0) +
                            "\nCantidad: " + file.getString(1));
                }
                /*
                insumo.setText("Producto: " +  spin.getSelectedItem().toString() +
                        "\nPrecio: " + oProducts.GetPrecio(spin.getSelectedItem().toString()) +
                        "\nCantidad: " + oProducts.GetCant(spin.getSelectedItem().toString()));
                 */
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                insumo.setText("");
            }
        });
    }

    public void tY(View view){
        String nom = spin.getSelectedItem().toString();
        float r = rtbar.getRating();
        Toast.makeText(this, "Gracias por darle " +r+ " estrellas al producto "+nom, Toast.LENGTH_SHORT).show();
    }
}