package com.proyecto.isamisa.Entidades;

import com.google.gson.annotations.SerializedName;

public class SolicitudItem {

    @SerializedName("id")
    private int id ;
    @SerializedName("line")
    private int line;
    @SerializedName("cantidad")
    private float cantidad;
    @SerializedName("solicitud")
    private Solicitud solicitud;
    @SerializedName("producto")
    private Producto producto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
