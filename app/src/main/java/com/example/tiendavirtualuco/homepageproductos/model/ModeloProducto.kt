package com.example.tiendavirtualuco.homepageproductos.model

data class ModeloProducto (
    val id: Int,
    val nombreProducto: String,
    val precioProducto: String,
    val imagenProducto: String,
    var oferta: Ofertas? = null
)