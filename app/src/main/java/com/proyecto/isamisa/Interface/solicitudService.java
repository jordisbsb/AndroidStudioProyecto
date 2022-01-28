package com.proyecto.isamisa.Interface;

import com.proyecto.isamisa.Entidades.Solicitud;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface solicitudService {

    @GET("solicitud/listar")
    Call<ArrayList<Solicitud>> getListaSolicitud();

    @POST("solicitar/crear")
    Call<Solicitud>crearSolicitud(@Body Solicitud solicitud);

}
