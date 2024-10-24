package com.example.tiendavirtualuco.trazabilidad.dao

import androidx.room.Query
import com.example.tiendavirtualuco.trazabilidad.entidad.ProductoEntity

interface UsuarioDAO {
    @Query("SELECT * FROM usuario")
    fun getAll(): List<ProductoEntity>

    @Query("SELECT * FROM usuario WHERE code = :code")
    fun findByCode(code: Int): ProductoEntity
}