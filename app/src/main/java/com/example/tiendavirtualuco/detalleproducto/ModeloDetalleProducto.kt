package com.example.tiendavirtualuco.detalleproducto

import java.io.Serializable

data class ModeloDetalleProducto (
    val nombre: String,
    val precio: String,
    val precioOriginal: String?,
    val descripcion: String,
    val imagen: String,
    val nombreTienda: String
) : Serializable
