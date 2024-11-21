package com.example.tiendavirtualuco.favoritos.client

import com.example.tiendavirtualuco.favoritos.dto.FavoritoDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FavoritosClient {
    @GET("api/v1/rest/favoritos/{email}")
    suspend fun getFavoritos(@Path("email") email: String): Response<List<FavoritoDTO>>
}