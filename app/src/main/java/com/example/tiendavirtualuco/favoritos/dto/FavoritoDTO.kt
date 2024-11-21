package com.example.tiendavirtualuco.favoritos.dto

data class FavoritoDTO(
    val id: String,
    val idFavorito: Int,
    val email: String,
    val nombre: String,
    val precio: Double,
    val origen: String
)