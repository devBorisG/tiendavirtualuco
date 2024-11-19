package com.example.tiendavirtualuco.favoritos.repository

import com.example.tiendavirtualuco.persistence.entity.ProductoConFavorito

interface FavoritoRepository {
    suspend fun agregarAFavoritos(productoId: Int)
    suspend fun obtenerFavoritos(): List<ProductoConFavorito>
    suspend fun eliminarFavorito(productoId: Int)
    suspend fun esFavorito(productoId: Int): Boolean
}