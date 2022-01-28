package com.proyecto.isamisa.Entidades;

import com.google.gson.annotations.SerializedName;

public class Producto {

@SerializedName("id")
private int id;
@SerializedName("nombre")
private String nombre;
@SerializedName("comentario")
private String comentario;
@SerializedName("subfamilia")
private SubFamilia subFamilia;
@SerializedName("unmedida")
private UnMedida unMedida;
@SerializedName("estado")
private String estado;

    public Producto( String nombre, String comentario, SubFamilia subFamilia, UnMedida unMedida) {
        this.nombre = nombre;
        this.comentario = comentario;
        this.subFamilia = subFamilia;
        this.unMedida = unMedida;
    }

    public Producto(int id, String nombre, String comentario, SubFamilia subFamilia, UnMedida unMedida, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.comentario = comentario;
        this.subFamilia = subFamilia;
        this.unMedida = unMedida;
        this.estado = estado;
    }

    public Producto() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public SubFamilia getSubFamilia() {
        return subFamilia;
    }

    public void setSubFamilia(SubFamilia subFamilia) {
        this.subFamilia = subFamilia;
    }

    public UnMedida getUnMedida() {
        return unMedida;
    }

    public void setUnMedida(UnMedida unMedida) {
        this.unMedida = unMedida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
