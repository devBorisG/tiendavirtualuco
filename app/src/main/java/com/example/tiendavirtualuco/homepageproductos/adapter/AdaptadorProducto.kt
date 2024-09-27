package com.example.tiendavirtualuco.homepageproductos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.homepageproductos.ModeloProducto

class AdaptadorProducto(private val listaProducto:List<ModeloProducto>) : RecyclerView.Adapter<VistaProducto>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VistaProducto {
        val layoutInflater = LayoutInflater.from(parent.context)
        return VistaProducto(layoutInflater.inflate(R.layout.trazabilidad_pagina_principal_productos_recyvleview, parent, false))
    }

    override fun getItemCount(): Int {
        return listaProducto.size
    }

    override fun onBindViewHolder(holder: VistaProducto, position: Int) {
        val item = listaProducto[position]
        holder.render(item)
    }
}