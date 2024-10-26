package com.example.tiendavirtualuco.persistence.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "favorito",
    foreignKeys = [ForeignKey(
        entity = ProductoEntity::class,
        parentColumns = ["id"],
        childColumns = ["productoId"],
        onDelete = ForeignKey.CASCADE
    )]
    )
data class FavoritoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productoId: Int
)