package com.proyecto.isamisa.Interface;

import com.proyecto.isamisa.Entidades.Producto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface productoService {

    @GET("producto/listar")
    Call<ArrayList<Producto>> getProducto();

    @GET("producto/buscar/{id}")
    Call<Producto> obtenerProducto(@Path("id") int id);

    @POST("producto/crear")
   Call<Producto> CrearProducto(@Body Producto producto);

@PUT("producto/actualizar/{id}")
Call<Producto>actualizarProducto(@Body Producto producto ,@Path("id") int id);

   @DELETE("producto/eliminar/{id}")
    Call<Producto> EliminarProducto(@Path("id") int id);

    @GET("producto/filtrar-producto/{term}")
    Call<ArrayList<Producto>> obtenerProductoPorTermino(@Path("term") String term);




}
