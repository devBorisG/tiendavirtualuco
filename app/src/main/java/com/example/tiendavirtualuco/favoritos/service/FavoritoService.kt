package com.example.tiendavirtualuco.favoritos.service

import com.example.tiendavirtualuco.persistence.dao.FavoritoDao
import com.example.tiendavirtualuco.persistence.entity.FavoritoEntity
import com.example.tiendavirtualuco.persistence.entity.ProductoConFavorito

class FavoritoService( private val favoritoDao: FavoritoDao
) {
    suspend fun insertarFavoritosDesdeApi(favoritos: List<FavoritoEntity>) {
        favoritoDao.insertarFavoritos(favoritos)
    }
    suspend fun obtenerProductosConFavoritos(email: String): List<ProductoConFavorito> {
        return favoritoDao.obtenerFavoritosConProductosPorEmail(email)
    }
}