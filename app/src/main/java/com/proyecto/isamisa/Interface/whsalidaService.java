package com.proyecto.isamisa.Interface;

import com.proyecto.isamisa.Entidades.Whingreso;
import com.proyecto.isamisa.Entidades.Whsalida;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface whsalidaService {

    @GET("whsalida/listar")
    Call<ArrayList<Whsalida>> listarsalida();

    @GET("/whsalida/buscar/{id}")
    Call<Whsalida> obtenerEgreso(@Path("id") int id);

}
