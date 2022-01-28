package com.proyecto.isamisa.Interface;

import com.proyecto.isamisa.Entidades.Transaccion;
import com.proyecto.isamisa.Entidades.Whingreso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface whingresoService {

    @GET("whingreso/listar")
    Call<ArrayList<Whingreso>> listaringreso();

    @GET("whingreso/buscar/{id}")
    Call<Whingreso> getingreso(@Path("id") int id);
}
