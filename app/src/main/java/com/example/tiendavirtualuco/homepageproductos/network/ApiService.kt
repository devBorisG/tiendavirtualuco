package com.example.tiendavirtualuco.homepageproductos.network

import com.example.tiendavirtualuco.homepageproductos.model.AuthRequest
import com.example.tiendavirtualuco.homepageproductos.model.AuthResponse
import com.example.tiendavirtualuco.homepageproductos.model.Ofertas
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("authenticate")
    suspend fun authenticate(@Body authRequest: AuthRequest): Response<AuthResponse>

    @GET("api/v1/rest/ofertas")
    suspend fun getOfertas(): Response<List<Ofertas>>
}