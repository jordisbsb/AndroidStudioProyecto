package com.proyecto.isamisa.Interface;

import com.proyecto.isamisa.Entidades.SubFamilia;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface subfamiliaService {

    @GET("subfamilia/listar")
    Call<ArrayList<SubFamilia>> getsubfamilia();

}
