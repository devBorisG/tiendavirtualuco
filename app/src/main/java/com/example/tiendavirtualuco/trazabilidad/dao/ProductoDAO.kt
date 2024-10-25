package com.example.tiendavirtualuco.trazabilidad.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.tiendavirtualuco.trazabilidad.entidad.ProductoEntity

@Dao
interface ProductoDAO {
    @Query("SELECT * FROM producto")
    fun getAll(): List<ProductoEntity>

    @Query("SELECT * FROM producto WHERE code = :code")
    fun findByCode(code: Int): ProductoEntity

}