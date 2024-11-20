package com.example.tiendavirtualuco.trazabilidad.adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.trazabilidad.Producto


// Cambié 'Product' a 'Producto' ya que esa es la clase que deberías estar utilizando
class AdaptadorProductos(private val productList: List<Producto>) :
    RecyclerView.Adapter<AdaptadorProductos.ProductViewHolder>() {


    private lateinit var mlistener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mlistener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.trazabilidad_pagina_principal_productos_recyvleview, parent, false)
        return ProductViewHolder(itemView,null)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val producto = productList[position]  // Asegúrate de utilizar tu clase Producto

        holder.bind(producto)
    }

    override fun getItemCount(): Int = productList.size


    class VistaDeProductos(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(producto: Producto) {  // Aquí usamos la clase Producto en lugar de Product
            // Aquí asignas los datos del producto a las vistas del layout
            // Ejemplo de cómo asignar valores a las vistas del layout:
            itemView.findViewById<TextView>(R.id.productName).text = producto.nombre
            itemView.findViewById<TextView>(R.id.productPrice).text = producto.precio.toString()
        }
    }

    class ProductViewHolder(itemView: View, private val listener: OnItemClickListener?) :
        RecyclerView.ViewHolder(itemView) {

        private val productName: TextView = itemView.findViewById(R.id.productName)
        private val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        //private val productImage: ImageView = itemView.findViewById(R.id.productImage) // Si tienes imagen

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(position) // Usa ? para llamada segura
                }
            }
        }

        fun bind(producto: Producto) {
            productName.text = producto.nombre
            productPrice.text = producto.precio.toString()

            // Comentamos la parte de Glide para uso futuro:
            /*
            Glide.with(itemView.context)
                .load(producto.imagenUrl)  // Aquí se cargaría la imagen del producto
                .into(productImage)
            */
        }
    }
}
