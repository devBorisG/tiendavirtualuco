package com.example.tiendavirtualuco.homepageproductos.adapter

import android.graphics.Paint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.homepageproductos.model.ModeloProducto

class VistaProducto(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val ivProducto: ImageView = itemView.findViewById(R.id.imageViewProducto)
    private val tvNombre: TextView = itemView.findViewById(R.id.nombre_producto)
    private val tvPrecioOriginal: TextView = itemView.findViewById(R.id.precio_original_producto)
    private val tvPrecioOferta: TextView = itemView.findViewById(R.id.precio_producto)
    private val tvPorcentajeDescuento: TextView = itemView.findViewById(R.id.porcentaje_descuento)

    fun render(producto: ModeloProducto) {
        // Cargar la imagen del producto usando Glide
        Glide.with(itemView.context)
            .load(producto.imagenProducto)
            .into(ivProducto)

        // Configurar el nombre del producto
        tvNombre.text = producto.nombreProducto

        // Verificar si el producto tiene una oferta
        if (producto.oferta != null) {
            // Mostrar precio original con tachado
            tvPrecioOriginal.text = "$${producto.oferta?.precioOriginal}"
            tvPrecioOriginal.paintFlags = tvPrecioOriginal.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            tvPrecioOriginal.visibility = View.VISIBLE

            // Mostrar precio de oferta
            tvPrecioOferta.text = "$${producto.oferta?.precioOferta}"
            tvPrecioOferta.visibility = View.VISIBLE

            // Mostrar porcentaje de descuento
            tvPorcentajeDescuento.text = "${producto.oferta?.porcentajeDescuento}"
            tvPorcentajeDescuento.visibility = View.VISIBLE
        } else {
            // No hay oferta, ocultar los campos relacionados
            tvPrecioOriginal.visibility = View.GONE
            tvPrecioOferta.text = "${producto.precioProducto}"
            tvPrecioOferta.paintFlags = tvPrecioOferta.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            tvPrecioOferta.visibility = View.VISIBLE
            tvPorcentajeDescuento.visibility = View.GONE
        }

    }
}
