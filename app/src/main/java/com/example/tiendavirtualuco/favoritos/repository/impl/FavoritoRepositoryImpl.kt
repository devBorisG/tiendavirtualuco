package com.example.tiendavirtualuco.favoritos.repository.impl

import com.example.tiendavirtualuco.favoritos.repository.FavoritoRepository
import com.example.tiendavirtualuco.persistence.dao.FavoritoDao
import com.example.tiendavirtualuco.persistence.entity.FavoritoEntity
import com.example.tiendavirtualuco.persistence.entity.ProductoConFavorito

class FavoritoRepositoryImpl(
    private val favoritoDao: FavoritoDao
) : FavoritoRepository {
    override suspend fun obtenerFavoritosConProductosPorEmail(email: String): List<ProductoConFavorito> {
        return favoritoDao.obtenerFavoritosConProductosPorEmail(email)
    }
    override suspend fun obtenerTodosFavoritosConProductos(): List<ProductoConFavorito> {
        return favoritoDao.obtenerTodosFavoritosConProductos()
    }
    override suspend fun eliminarFavoritoPorId(favoritoId: Int) {
        favoritoDao.eliminarFavoritoPorId(favoritoId)
    }
    override suspend fun insertarFavorito(favorito: FavoritoEntity) {
        favoritoDao.insertarFavorito(favorito)
    }
}