package com.example.tiendavirtualuco.persistence.dao

import androidx.room.*
import com.example.tiendavirtualuco.persistence.entity.ProductoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductoDao {

    @Insert
    suspend fun insertProducto(producto: ProductoEntity)

    @Insert
    suspend fun insertProductos(productos: List<ProductoEntity>)

    @Query("SELECT * FROM producto")
    suspend fun getAllProductos(): List<ProductoEntity>

    @Query("DELETE FROM producto")
    suspend fun deleteAllProductos()

    @Query("DELETE FROM sqlite_sequence WHERE name='producto'")
    suspend fun resetId()
}