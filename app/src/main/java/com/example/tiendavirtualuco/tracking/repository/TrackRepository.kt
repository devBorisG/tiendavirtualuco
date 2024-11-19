package com.example.tiendavirtualuco.tracking.repository

import com.example.tiendavirtualuco.tracking.model.Tracks
import com.example.tiendavirtualuco.tracking.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TrackRepository {
    suspend fun getTracks(): Result<List<Tracks>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitClient.instance.getTracks()
                if (response.isSuccessful) {
                    val track = response.body()
                    if (track != null) {
                        Result.success(track)
                    } else {
                        Result.failure(Exception("Respuesta vacía del servidor"))
                    }
                } else {
                    Result.failure(Exception("Error al obtener los datos de seguimiento: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}