package com.lastview.Objetos;

import java.util.Arrays;

public class Products {
    private int ID;
    private String[] nombre = {"Papel OCB","Tabaco Vainilla","Filtro Delgado"};
    private int[] cant = {10, 20, 15};
    private int[] precio = {1500, 2600, 2000};

    public Products() {
    }

    public Products(int ID, String[] nombre, int[] cant, int[] precio) {
        this.ID = ID;
        this.nombre = nombre;
        this.cant = cant;
        this.precio = precio;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String[] getNombre() {
        return nombre;
    }

    public void setNombre(String[] nombre) {
        this.nombre = nombre;
    }

    public int[] getCant() {
        return cant;
    }

    public void setCant(int[] cant) {
        this.cant = cant;
    }

    public int[] getPrecio() {
        return precio;
    }

    public void setPrecio(int[] precio) {
        this.precio = precio;
    }

    public Integer GetPrecio(String selected)
    {
        Integer resultado = 0;
        String Productos[] = getNombre();
        int Precios[] = getPrecio();

        Integer getIndex = Arrays.asList(Productos).indexOf(selected);

        resultado = Precios[getIndex];

        return resultado;
    }

    public Integer GetCant(String selected){
        Integer result = 0;
        String Productos[] = getNombre();;
        int Cant[] = getCant();

        Integer getIndex = Arrays.asList(Productos).indexOf(selected);

        result = Cant[getIndex];

        return result;
    }
}
