package com.example.tiendavirtualuco.misproductos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.misproductos.ModeloMisProductos

class AdaptadorMisProductos(private val listaMisProducto:List<ModeloMisProductos>) : RecyclerView.Adapter<VistaMisProductos>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VistaMisProductos {
        val layoutInflater = LayoutInflater.from(parent.context)
        return VistaMisProductos(layoutInflater.inflate(R.layout.pagina_principal_productos_recyvleview, parent, false))
    }

    override fun onBindViewHolder(holder: VistaMisProductos, position: Int) {
        val item = listaMisProducto[position]
        holder.render(item) // hubo modificacion jero
    }

    override fun getItemCount(): Int {
        return listaMisProducto.size
    }

}