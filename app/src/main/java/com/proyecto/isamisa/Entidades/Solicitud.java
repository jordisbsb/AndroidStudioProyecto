package com.proyecto.isamisa.Entidades;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Solicitud {
    @SerializedName("id")
    private int id;
    @SerializedName("descripcion")
    private String descripcion;
    @SerializedName("estado")
    private String estado;
    @SerializedName("fecha")
    private Date fecha;
    @SerializedName("numtipo")
    private int numtipo;
    @SerializedName("nomtipo")
    private String nomtipo;
    @SerializedName("persona")
    private Persona persona;
    @SerializedName("subalmacen")
    private SubAlmacen subalmacen;
    @SerializedName("tiposol")
    private TipoTransaccion tiposol;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNumtipo() {
        return numtipo;
    }

    public void setNumtipo(int numtipo) {
        this.numtipo = numtipo;
    }

    public String getNomtipo() {
        return nomtipo;
    }

    public void setNomtipo(String nomtipo) {
        this.nomtipo = nomtipo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public SubAlmacen getSubalmacen() {
        return subalmacen;
    }

    public void setSubalmacen(SubAlmacen subalmacen) {
        this.subalmacen = subalmacen;
    }

    public TipoTransaccion getTiposol() {
        return tiposol;
    }

    public void setTiposol(TipoTransaccion tiposol) {
        this.tiposol = tiposol;
    }
}
