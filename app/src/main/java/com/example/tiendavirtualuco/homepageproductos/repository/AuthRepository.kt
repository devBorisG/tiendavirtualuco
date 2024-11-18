package com.example.tiendavirtualuco.homepageproductos.repository
import com.example.tiendavirtualuco.homepageproductos.model.AuthRequest
import com.example.tiendavirtualuco.homepageproductos.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository {
    private val predefinedUsername = "prueba"
    private val predefinedPassword = "prueba"

    suspend fun authenticate(): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitClient.instance.authenticate(AuthRequest(predefinedUsername, predefinedPassword))
                if (response.isSuccessful) {
                    val authResponse = response.body()
                    if (authResponse != null) {
                        Result.success(authResponse.token)
                    } else {
                        Result.failure(Exception("Respuesta vacía del servidor"))
                    }
                } else {
                    Result.failure(Exception("Error de autenticación: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}