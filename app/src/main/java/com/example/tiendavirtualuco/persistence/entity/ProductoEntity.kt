package com.example.tiendavirtualuco.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tiendavirtualuco.persistence.data.cloud.init
import java.util.UUID

@Entity(tableName = "producto")
data class ProductoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val cantidad: Int,
    val precio: Double,
    val descripcion: String,
    val url_imagen: String,
    val es_oferta: Boolean,
    val precio_oferta: Double,
    val precio_envio: Double,
)
