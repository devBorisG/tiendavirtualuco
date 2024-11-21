package com.example.tiendavirtualuco.tienda.network

import com.example.tiendavirtualuco.tienda.model.ModeloTienda
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("api/v1/rest/tienda/{id}")
    suspend fun getTiendaDetalles(@Path("id") idTienda: Int): Response<ModeloTienda>

}