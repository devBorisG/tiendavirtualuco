package com.example.tiendavirtualuco.homepageproductos.model

import java.io.Serializable

data class ModeloProducto (
    val id: Int,
    val nombreProducto: String,
    val cantidad: Int,
    val precioProducto: Double,
    val descripcion: String,
    val imagenProducto: String,
    val es_oferta: Boolean,
    val precio_oferta: Double,
    val precio_envio: Double,
    val porcentaje_descuento: String
) : Serializable