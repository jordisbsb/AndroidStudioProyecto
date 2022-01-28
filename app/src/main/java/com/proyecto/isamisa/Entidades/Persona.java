package com.proyecto.isamisa.Entidades;

import com.google.gson.annotations.SerializedName;
import com.proyecto.isamisa.Entidades.Distrito;

public class Persona {
    @SerializedName("id")
    private int id;
    @SerializedName("nombre1")
    private String nombre1;
    @SerializedName("nombre2")
    private String nombre2;
    @SerializedName("apepat")
    private String apepat;
    @SerializedName("apemat")
    private String apemat;
    @SerializedName("dni")
    private String dni;
    @SerializedName("telefono")
    private String telefono;
    @SerializedName("direccion")
    private String direccioon;
    @SerializedName("correo")
    private String correo;
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

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApepat() {
        return apepat;
    }

    public void setApepat(String apepat) {
        this.apepat = apepat;
    }

    public String getApemat() {
        return apemat;
    }

    public void setApemat(String apemat) {
        this.apemat = apemat;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccioon() {
        return direccioon;
    }

    public void setDireccioon(String direccioon) {
        this.direccioon = direccioon;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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
