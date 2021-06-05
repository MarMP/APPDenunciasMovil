package com.example.appdenuncias.retrofit_data;

import com.example.appdenuncias.ItemListComunicaciones;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitApiService {

    @GET("listarComunicaciones.php")
    Call<List<ItemListComunicaciones>> getItemsComunicaciones(@Query("id_empleado") String id);

    /*Consulta con el id metido por get
    @GET("listarComunicaciones.php?id_empleado=16")
    Call<List<ItemListComunicaciones>> getItemsComunicaciones(); */

    /*@GET("listarComunicaciones.php")
    Call<List<ItemListComunicaciones>> getItemsComunicaciones();*/

}
