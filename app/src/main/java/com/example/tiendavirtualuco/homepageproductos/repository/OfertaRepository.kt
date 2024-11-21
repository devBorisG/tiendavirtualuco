package com.example.tiendavirtualuco.homepageproductos.repository

import com.example.tiendavirtualuco.homepageproductos.model.Ofertas
import com.example.tiendavirtualuco.homepageproductos.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OfertaRepository {
    suspend fun getOfertas(): Result<List<Ofertas>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitClient.instance.getOfertas()
                if (response.isSuccessful) {
                    val ofertas = response.body()
                    if (ofertas != null) {
                        Result.success(ofertas)
                    } else {
                        Result.failure(Exception("Respuesta vacía del servidor"))
                    }
                } else {
                    Result.failure(Exception("Error al obtener las ofertas: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}