package com.example.tiendavirtualuco.favoritos.service

import com.example.tiendavirtualuco.homepageproductos.network.ApiService
import com.example.tiendavirtualuco.persistence.entity.FavoritoEntity
import com.example.tiendavirtualuco.persistence.entity.ProductoEntity

class ApiFavoritoService(private val apiService: ApiService) {
    suspend fun obtenerProductosYFavoritosApi(email: String): Pair<List<ProductoEntity>, List<FavoritoEntity>> {
        val response = apiService.getOfertas()
        if (response.isSuccessful) {
            val ofertas = response.body()
            if (!ofertas.isNullOrEmpty()) {
                val productos = ofertas.map { oferta ->
                    ProductoEntity(
                        id = oferta.idProducto,
                        nombre = "Producto ${oferta.idProducto}", // Asume nombres genéricos si no los tienes.
                        cantidad = 1,
                        precio = oferta.precioOriginal,
                        descripcion = "Descripción del producto ${oferta.idProducto}",
                        url_imagen = "",
                        es_oferta = true,
                        precio_oferta = oferta.precioOferta,
                        precio_envio = 0.0
                    )
                }
                val favoritos = ofertas.map { oferta ->
                    FavoritoEntity(
                        productoId = oferta.idProducto,
                        email = email
                    )
                }
                return Pair(productos, favoritos)
            }
        }
        throw Exception("Error al obtener ofertas desde el API")
    }
}