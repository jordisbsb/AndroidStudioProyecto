package com.proyecto.isamisa.Entidades;

import com.google.gson.annotations.SerializedName;

public class SubFamilia {

    @SerializedName("id")
    private int id;
    @SerializedName("nombre")
    private String nombre;

    @SerializedName("familia")
    private Familia familia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }
}
