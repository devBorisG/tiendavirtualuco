package com.example.tiendavirtualuco.favoritos.repository.impl

import com.example.tiendavirtualuco.favoritos.repository.FavoritoRepository
import com.example.tiendavirtualuco.persistence.dao.FavoritoDao
import com.example.tiendavirtualuco.persistence.entity.FavoritoEntity
import com.example.tiendavirtualuco.persistence.entity.ProductoConFavorito

class FavoritoRepositoryImpl(
    private val favoritoDao: FavoritoDao
) : FavoritoRepository {

    override suspend fun agregarAFavoritos(productoId: Int) {
        val favorito = FavoritoEntity(productoId = productoId)
        favoritoDao.insertar(favorito)
    }

    override suspend fun obtenerFavoritos(): List<ProductoConFavorito> {
        return favoritoDao.obtenerFavoritos()
    }

    override suspend fun eliminarFavorito(productoId: Int) {
        favoritoDao.eliminarFavoritoPorProductoId(productoId)
    }

    override suspend fun esFavorito(productoId: Int): Boolean {
        return favoritoDao.esFavorito(productoId)
    }
}