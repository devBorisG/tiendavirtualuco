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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.trazabilidad_item_product_recicleview, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val producto = productList[position]  // Asegúrate de utilizar tu clase Producto
        holder.bind(producto)
    }

    override fun getItemCount() = productList.size

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(producto: Producto) {  // Aquí usamos la clase Producto en lugar de Product
            // Aquí asignas los datos del producto a las vistas del layout
            // Ejemplo de cómo asignar valores a las vistas del layout:
            itemView.findViewById<TextView>(R.id.productName).text = producto.nombre
            itemView.findViewById<TextView>(R.id.productPrice).text = producto.precio.toString()
        }
    }
}
