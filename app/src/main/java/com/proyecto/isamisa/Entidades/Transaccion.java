package com.proyecto.isamisa.Entidades;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Transaccion {
    @SerializedName("id")
    private int id;
    @SerializedName("fecha")
    private Date fecha;
    @SerializedName("nomtipo")
    String nomtipo;
    @SerializedName("numtipo")
    int numtipo;
    @SerializedName("subalmacen")
    private SubAlmacen subalmacen;
    @SerializedName("persona")
    private Persona persona;
    @SerializedName("tipotransaccion")
    private TipoTransaccion tipotransaccion;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNomtipo() {
        return nomtipo;
    }

    public void setNomtipo(String nomtipo) {
        this.nomtipo = nomtipo;
    }

    public int getNumtipo() {
        return numtipo;
    }

    public void setNumtipo(int numtipo) {
        this.numtipo = numtipo;
    }

    public SubAlmacen getSubalmacen() {
        return subalmacen;
    }

    public void setSubalmacen(SubAlmacen subalmacen) {
        this.subalmacen = subalmacen;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public TipoTransaccion getTipotransaccion() {
        return tipotransaccion;
    }

    public void setTipotransaccion(TipoTransaccion tipotransaccion) {
        this.tipotransaccion = tipotransaccion;
    }
}
