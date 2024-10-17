package com.example.tiendavirtualuco.trazabilidad.entidad

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "producto")

data class ProductoEntity(
    @PrimaryKey val code: Int,  // Es un valor constante, por eso usamos 'val'

    @ColumnInfo(name = "nombre") var name: String,  // Se usa 'var' para que Room pueda actualizar el valor
    @ColumnInfo(name = "descripcion") var description: String = "",  // Usamos String en lugar de Int
    @ColumnInfo(name = "cantidad") var quantity: Int = 0,  // Los nombres se pueden personalizar con 'name'
    @ColumnInfo(name = "precio") var price: Double = 0.0
)