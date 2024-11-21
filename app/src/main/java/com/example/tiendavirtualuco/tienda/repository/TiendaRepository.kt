package com.example.tiendavirtualuco.tienda.repository

import com.example.tiendavirtualuco.tienda.model.ModeloTienda
import com.example.tiendavirtualuco.tienda.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class TiendaRepository {

    suspend fun getDetallesTienda(idTienda: Int): Result<ModeloTienda> {
        return withContext(Dispatchers.IO) {
            try {
                val response : Response<ModeloTienda> = RetrofitClient.instance.getTiendaDetalles(idTienda)
                if (response.isSuccessful) {
                    response.body()?.let { Result.success(it) }
                        ?: Result.failure(Exception("Respuesta vacía"))
                } else {
                    Result.failure(Exception("Error al obtener detalles: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
