package com.example.tiendavirtualuco.favoritos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.favoritos.adapter.AdaptadorFavorito

class MisFavoritosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mis_favoritos)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler_favoritos)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerView.adapter = AdaptadorFavorito(ProveedorFavorito.listaFavorito)
    }
}