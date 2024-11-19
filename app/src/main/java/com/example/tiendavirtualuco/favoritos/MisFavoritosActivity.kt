package com.example.tiendavirtualuco.favoritos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.favoritos.adapter.AdaptadorFavorito
import com.example.tiendavirtualuco.favoritos.model.FavoritoViewModel
import com.example.tiendavirtualuco.favoritos.model.FavoritoViewModelFactory
import com.example.tiendavirtualuco.favoritos.repository.impl.FavoritoRepositoryImpl
import com.example.tiendavirtualuco.persistence.data.local.DatabaseProvider
import com.example.tiendavirtualuco.persistence.data.local.database.AppDatabase

class MisFavoritosActivity : AppCompatActivity() {

    private lateinit var favoritoViewModel: FavoritoViewModel
    private lateinit var adaptadorFavorito: AdaptadorFavorito

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mis_favoritos)
        inicializarBD()
        initRecyclerView()
        cargarFavoritos()
    }
    private fun inicializarBD() {
        val favoritoRepository = FavoritoRepositoryImpl(DatabaseProvider.getDatabase(this).favoritoDao())
        // Crear la fábrica
        val factory = FavoritoViewModelFactory(favoritoRepository)
        // Inicializar el ViewModel con la fábrica
        favoritoViewModel = ViewModelProvider(this, factory)[FavoritoViewModel::class.java]
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_favoritos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adaptadorFavorito = AdaptadorFavorito(mutableListOf())
        recyclerView.adapter = adaptadorFavorito
    }

    private fun cargarFavoritos() {
        favoritoViewModel.favoritos.observe(this) { listaFavoritos ->
            // Convertir los datos de ProductoConFavorito a ModeloFavorito
            val listaConvertida = listaFavoritos.map {
                ModeloFavorito(
                    nombre = it.producto.nombre,
                    precio = it.producto.precio.toString(),
                    precioOriginal = it.producto.precio_oferta.toString() ?: "",
                    imagen = it.producto.url_imagen
                )
            }
            adaptadorFavorito.actualizarLista(listaConvertida)
        }
        // Cargar datos desde la base de datos
        favoritoViewModel.cargarFavoritos()
    }
}
