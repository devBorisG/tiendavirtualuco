package com.example.tiendavirtualuco.tienda.model

data class ModeloTienda(
    val id: String,
    val idTienda: Int,
    val nombre: String,
    val webAddress: String,
    val descripcion: String,
    val tipo: String,
    val direccion: String,
    val ciudad: String,
    val pais: String,
    val nombreCourier: String
)
