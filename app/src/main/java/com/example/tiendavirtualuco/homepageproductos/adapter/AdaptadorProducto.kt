package com.example.tiendavirtualuco.homepageproductos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.detalleproducto.ModeloDetalleProducto
import com.example.tiendavirtualuco.homepageproductos.model.ModeloProducto

class AdaptadorProducto(private val listaProducto:MutableList<ModeloProducto>,
                        private val onProductoClick: (ModeloDetalleProducto) -> Unit) : RecyclerView.Adapter<VistaProducto>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VistaProducto {
        val layoutInflater = LayoutInflater.from(parent.context)
        return VistaProducto(layoutInflater.inflate(R.layout.pagina_principal_productos_recyvleview, parent, false))
    }

    override fun getItemCount(): Int = listaProducto.size

    override fun onBindViewHolder(holder: VistaProducto, position: Int) {
        val item = listaProducto[position]
        holder.render(item)
        // Configurar clic para enviar el producto
        holder.itemView.setOnClickListener {
            val detalleProducto = ModeloDetalleProducto(
                nombre = item.nombreProducto,
                precio = (if (item.es_oferta) item.precio_oferta else item.precioProducto).toString(),
                precioOriginal = (if (item.es_oferta) item.precioProducto else null).toString(),
                descripcion = item.descripcion,
                imagen = item.imagenProducto,
                nombreTienda = "Tienda Virtual UCO"
            )
            onProductoClick(detalleProducto)
        }
    }

    fun updateProductos(nuevosProductos: List<ModeloProducto>) {
        val diffCallback = ProductoDiffCallback(this.listaProducto, nuevosProductos)
        val diffResult = androidx.recyclerview.widget.DiffUtil.calculateDiff(diffCallback)

        this.listaProducto.clear()
        this.listaProducto.addAll(nuevosProductos)
        diffResult.dispatchUpdatesTo(this)
    }
}

class ProductoDiffCallback(
    private val oldList: List<ModeloProducto>,
    private val newList: List<ModeloProducto>
) : androidx.recyclerview.widget.DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    // Define cómo determinar si dos items son el mismo
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    // Define cómo determinar si el contenido de los items es el mismo
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}