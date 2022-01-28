package com.proyecto.isamisa.Entidades;

import com.google.gson.annotations.SerializedName;

public class Whsalida extends Transaccion {

    @SerializedName("centrocosto")
    private CentroCosto centroCosto;


    public CentroCosto getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(CentroCosto centroCosto) {
        this.centroCosto = centroCosto;
    }
}
