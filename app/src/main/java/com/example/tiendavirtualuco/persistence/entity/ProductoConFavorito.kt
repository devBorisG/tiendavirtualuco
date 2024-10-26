package com.example.tiendavirtualuco.persistence.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ProductoConFavorito(
    @Embedded val producto: ProductoEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "productoId"
    )
    val favorito: FavoritoEntity?
)