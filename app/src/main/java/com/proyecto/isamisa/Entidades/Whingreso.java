package com.proyecto.isamisa.Entidades;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Whingreso extends Transaccion {

    @SerializedName("docreferencia")
    private  String docreferencia;
    @SerializedName("empresa")
    private  Empresa empresa;

    private ArrayList<SolicitudItem> solicitudItems;

    public ArrayList<SolicitudItem> getSolicitudItems() {
        return solicitudItems;
    }

    public void setSolicitudItems(ArrayList<SolicitudItem> solicitudItems) {
        this.solicitudItems = solicitudItems;
    }

    public String getDocreferencia() {
        return docreferencia;
    }

    public void setDocreferencia(String docreferencia) {
        this.docreferencia = docreferencia;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }



}
