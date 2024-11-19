package com.example.tiendavirtualuco.tracking.network

import com.example.tiendavirtualuco.tracking.model.AuthRequest
import com.example.tiendavirtualuco.tracking.model.AuthResponse
import com.example.tiendavirtualuco.tracking.model.Tracks
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("authenticate")
    suspend fun authenticate(@Body authRequest: AuthRequest): Response<AuthResponse>

    @GET("api/v1/rest/track/1")
    suspend fun getTracks(): Response<List<Tracks>>
}