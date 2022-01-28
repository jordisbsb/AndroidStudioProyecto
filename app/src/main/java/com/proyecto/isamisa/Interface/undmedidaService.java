package com.proyecto.isamisa.Interface;

import com.proyecto.isamisa.Entidades.UnMedida;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface undmedidaService {

@GET("unmedida/listar")
Call<ArrayList<UnMedida>> getUnMedida();


}
