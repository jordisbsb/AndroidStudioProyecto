package com.proyecto.isamisa.Entidades;

import com.google.gson.annotations.SerializedName;

public class Empresa {

    @SerializedName("id")
    private int id;
    @SerializedName("razonsocial")
    private String razonsocial;
    @SerializedName("ruc")
    private String ruc;
    @SerializedName("contacto")
    private String contacto;
    @SerializedName("puestocontacto")
    private String puestocontacto;
    @SerializedName("telefono")
    private String telefono;
    @SerializedName("direccion")
    private String direccion;
    @SerializedName("correo")
    private String correo;
    @SerializedName("actividad")
    private String actividad;
    @SerializedName("estado")
    private String estado;
    @SerializedName("distrito")
    private Distrito distrito;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getPuestocontacto() {
        return puestocontacto;
    }

    public void setPuestocontacto(String puestocontacto) {
        this.puestocontacto = puestocontacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }
}
