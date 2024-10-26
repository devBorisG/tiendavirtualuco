package com.example.tiendavirtualuco.persistence.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.tiendavirtualuco.persistence.entity.FavoritoEntity
import com.example.tiendavirtualuco.persistence.entity.ProductoConFavorito

interface FavoritoDao {
    @Transaction
    @Query("SELECT * FROM producto WHERE id = :productoId")
    suspend fun obtenerConIdProducto(productoId: Int): ProductoConFavorito?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(favorito: FavoritoEntity)
}