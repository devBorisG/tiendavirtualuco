package com.example.tiendavirtualuco.homepageproductos.mapper

import com.example.tiendavirtualuco.persistence.entity.ProductoEntity

fun toProductoEntity(
    id: Int,
    nombre: String,
    cantidad: Int,
    precio: Double,
    descripcion: String,
    url_imagen: String,
    es_oferta: Boolean,
    precio_oferta: Double,
    precio_envio: Double,
): ProductoEntity {
    return ProductoEntity(
        id = id,
        nombre = nombre,
        cantidad = cantidad,
        precio = precio,
        descripcion = descripcion,
        url_imagen = url_imagen,
        es_oferta = es_oferta,
        precio_oferta = precio_oferta,
        precio_envio = precio_envio,
    )
}