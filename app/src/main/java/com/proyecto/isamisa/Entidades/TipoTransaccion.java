package com.proyecto.isamisa.Entidades;

import com.google.gson.annotations.SerializedName;

public class TipoTransaccion {
    @SerializedName("id")
    private int id;
    @SerializedName("tipo")
    private String tipo;
    @SerializedName("nombre")
    private String nombre;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
