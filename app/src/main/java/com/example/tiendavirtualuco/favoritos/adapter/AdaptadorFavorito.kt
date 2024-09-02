package com.example.tiendavirtualuco.favoritos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.favoritos.ModeloFavorito

class AdaptadorFavorito(private val listFavorito: List<ModeloFavorito> ): RecyclerView.Adapter<VistaFavorito>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VistaFavorito {
        val layoutInflater = LayoutInflater.from(parent.context)
        return VistaFavorito(layoutInflater.inflate(R.layout.mis_favoritos_recyclerview, parent, false))
    }

    override fun getItemCount(): Int {
        return listFavorito.size
    }

    override fun onBindViewHolder(holder: VistaFavorito, position: Int) {
        val item = listFavorito[position]
        holder.render(item)
    }
}