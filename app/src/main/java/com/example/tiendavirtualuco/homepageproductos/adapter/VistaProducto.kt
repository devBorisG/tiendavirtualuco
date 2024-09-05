package com.example.tiendavirtualuco.homepageproductos.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendavirtualuco.homepageproductos.ModeloProducto
//import com.bumptech.glide.Glide

class VistaProducto(view : View) : RecyclerView.ViewHolder(view) {
    val imagenProducto = view.findViewById<android.widget.ImageView>(com.example.tiendavirtualuco.R.id.imageViewProducto)
    val nombreProducto = view.findViewById<android.widget.TextView>(com.example.tiendavirtualuco.R.id.nombre_producto)
    val precioProducto = view.findViewById<android.widget.TextView>(com.example.tiendavirtualuco.R.id.precio_producto)

    fun render(modeloProducto: ModeloProducto) {
        nombreProducto.text = modeloProducto.nombreProducto
        precioProducto.text = modeloProducto.precioProducto
        /*Glide.with(itemView.context)
            .load(modeloProducto.imagenProducto)
            .override(150, 150)
            .centerCrop()
            .into(imagenProducto)

         */
    }
}