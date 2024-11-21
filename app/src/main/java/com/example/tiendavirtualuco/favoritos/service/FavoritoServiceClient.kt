package com.example.tiendavirtualuco.favoritos.service

import com.example.tiendavirtualuco.favoritos.client.FavoritosClient
import com.example.tiendavirtualuco.favoritos.dto.FavoritoDTO
import com.example.tiendavirtualuco.homepageproductos.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoritoServiceClient {
    private val favoritosClient: FavoritosClient = RetrofitClient.createService(FavoritosClient::class.java)

    suspend fun getFavoritos(email: String): Result<List<FavoritoDTO>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = favoritosClient.getFavoritos(email)
                if (response.isSuccessful) {
                    val favoritos = response.body()
                    if (favoritos != null) {
                        Result.success(favoritos)
                    } else {
                        Result.failure(Exception("Respuesta vacía del servidor"))
                    }
                } else {
                    Result.failure(Exception("Ha ocurrido un error al obtener los favoritos: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}