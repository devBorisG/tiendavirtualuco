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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarFavorito(favorito: FavoritoEntity)
    @Insert
    suspend fun insertarFavoritos(favoritos: List<FavoritoEntity>)
    @Transaction
    @Query("SELECT * FROM producto INNER JOIN favorito ON producto.id = favorito.productoId WHERE favorito.email = :email")
    suspend fun obtenerFavoritosConProductosPorEmail(email: String): List<ProductoConFavorito>
    @Transaction
    @Query("SELECT * FROM producto INNER JOIN favorito ON producto.id = favorito.productoId")
    suspend fun obtenerTodosFavoritosConProductos(): List<ProductoConFavorito>
    @Query("DELETE FROM favorito WHERE id = :favoritoId")
    suspend fun eliminarFavoritoPorId(favoritoId: Int)
}