package com.example.tiendavirtualuco.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.tiendavirtualuco.persistence.entity.FavoritoEntity
import com.example.tiendavirtualuco.persistence.entity.ProductoConFavorito

@Dao
interface FavoritoDao {
    @Transaction
    @Query("SELECT * FROM producto WHERE id IN (SELECT productoId FROM favorito)")
    suspend fun obtenerFavoritos(): List<ProductoConFavorito>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(favorito: FavoritoEntity)
    @Query("DELETE FROM favorito WHERE productoId = :productoId")
    suspend fun eliminarFavoritoPorProductoId(productoId: Int)
    @Query("SELECT COUNT(*) > 0 FROM favorito WHERE productoId = :productoId")
    suspend fun esFavorito(productoId: Int): Boolean
}