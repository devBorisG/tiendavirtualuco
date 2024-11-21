package com.example.tiendavirtualuco.favoritos.repository

import com.example.tiendavirtualuco.persistence.entity.FavoritoEntity
import com.example.tiendavirtualuco.persistence.entity.ProductoConFavorito

interface FavoritoRepository {
    suspend fun obtenerFavoritosConProductosPorEmail(email: String): List<ProductoConFavorito>
    suspend fun insertarFavorito(favorito: FavoritoEntity)
    suspend fun eliminarFavoritoPorId(favoritoId: Int)
    suspend fun obtenerTodosFavoritosConProductos(): List<ProductoConFavorito>
}