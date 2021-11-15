package com.lastview.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    final String CREAR_TABLA_USUARIO="CREATE TABLE IF NOT EXISTS usuarios (ID INTEGER primary key AUTOINCREMENT, username TEXT, password TEXT)";
    final String CREAR_TABLA_PRODUCTOS="CREATE TABLE productos (ID INTEGER primary key AUTOINCREMENT, nombre TEXT, cant INTEGER, precio INTEGER)";

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_USUARIO);
        db.execSQL("CREATE TABLE IF NOT EXISTS productos (ID INTEGER primary key AUTOINCREMENT, nombre TEXT, cant INTEGER, precio INTEGER);");
        db.execSQL("CREATE TABLE IF NOT EXISTS pedidos (ID INTEGER primary key AUTOINCREMENT, productos TEXT, cliente INTEGER)");

        db.execSQL("INSERT INTO productos (ID, nombre, cant, precio) VALUES (null, 'Papel OCB', 10, 1500)");
        db.execSQL("INSERT INTO productos (ID, nombre, cant, precio) VALUES (null, 'Tabaco Vainilla', 20, 2600)");
        db.execSQL("INSERT INTO productos (ID, nombre, cant, precio) VALUES (null, 'Filtro Delgado', 15, 2000)");
        db.execSQL("INSERT INTO usuarios (ID, username, password) VALUES (null, 'hugo', '123')");
        db.execSQL("INSERT INTO pedidos (ID, productos, cliente) VALUES (null, 'Filtros OCB', 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS productos");
        onCreate(db);
    }
}
