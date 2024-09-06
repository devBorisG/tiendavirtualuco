package com.example.tiendavirtualuco.favoritos.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tiendavirtualuco.favoritos.ModeloFavorito

class VistaFavorito(view: View) : RecyclerView.ViewHolder(view) {
    val imagenProducto = view.findViewById<android.widget.ImageView>(com.example.tiendavirtualuco.R.id.imgProductoFv)
    val nombre = view.findViewById<android.widget.TextView>(com.example.tiendavirtualuco.R.id.tvTituloProductoFv)
    val precio = view.findViewById<android.widget.TextView>(com.example.tiendavirtualuco.R.id.tvPrecioFv)
    val precioOriginal = view.findViewById<android.widget.TextView>(com.example.tiendavirtualuco.R.id.tvPrecioOriginalFv)

    fun render(modelo: ModeloFavorito) {
        nombre.text = modelo.nombre
        precio.text = modelo.precio
        precioOriginal.text = modelo.precioOriginal
        Glide.with(itemView.context)
            .load(modelo.imagen)
            .override(150, 140)
            .centerCrop()
            .into(imagenProducto)
    }
}