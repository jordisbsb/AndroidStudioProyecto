package com.proyecto.isamisa.Interface;

;

import com.proyecto.isamisa.Entidades.Persona;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface personaService {

    @GET("personas/listar")
    Call<List<Persona>> getpersona();
}
