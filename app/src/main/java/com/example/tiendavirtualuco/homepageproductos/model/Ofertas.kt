package com.example.tiendavirtualuco.homepageproductos.model

data class Ofertas(
    val id: String,
    val idProducto: Int,
    val precioOferta: Double,
    val precioOriginal: Double,
    val porcentajeDescuento: String
)
