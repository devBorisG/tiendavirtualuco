package com.example.tiendavirtualuco.favoritos

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.favoritos.adapter.AdaptadorFavorito
import com.example.tiendavirtualuco.favoritos.client.FavoritosClient
import com.example.tiendavirtualuco.favoritos.service.ApiFavoritoService
import com.example.tiendavirtualuco.favoritos.service.FavoritoServiceClient
import com.example.tiendavirtualuco.homepageproductos.network.RetrofitClient
import com.example.tiendavirtualuco.persistence.dao.FavoritoDao
import com.example.tiendavirtualuco.persistence.dao.ProductoDao
import com.example.tiendavirtualuco.persistence.data.local.DatabaseProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MisFavoritosActivity : AppCompatActivity() {
    private lateinit var adaptadorFavorito: AdaptadorFavorito
    private lateinit var productoDao: ProductoDao
    private lateinit var favoritoDao: FavoritoDao
    private lateinit var favoritoClient: FavoritoServiceClient
    private val apiFavoritoService = ApiFavoritoService(RetrofitClient.instance)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RetrofitClient.init(this)
        setContentView(R.layout.mis_favoritos)
        val database = DatabaseProvider.getDatabase(this)
        productoDao = database.productoDao()
        favoritoDao = database.favoritoDao()
        favoritoClient = FavoritoServiceClient()
        initRecyclerView()
//        lifecycleScope.launch(Dispatchers.IO) {
//            try {
//                val dao = database.openHelper.writableDatabase
//                // Obtener el listado de tablas
//                val cursor = dao.query("SELECT name FROM sqlite_master WHERE type='table'")
//                while (cursor.moveToNext()) {
//                    val tableName = cursor.getString(0)
//                    if (tableName != "android_metadata" && tableName != "sqlite_sequence") {
//                        dao.execSQL("DELETE FROM $tableName") // Limpia la tabla
//                    }
//                }
//                cursor.close()
//            } catch (e: Exception) {
//                Log.e("Reiniciar DB", "Error al reiniciar la base de datos", e)
//            }
//        }
        cargarFavoritos()
    }
    /**
     * Inicializa el RecyclerView y el Adaptador
     */
    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_favoritos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adaptadorFavorito = AdaptadorFavorito(mutableListOf())
        recyclerView.adapter = adaptadorFavorito
    }

    /**
     * Carga favoritos desde el API y los muestra en el RecyclerView
     */
    private fun cargarFavoritos() {
        val email = "juanfelipe@uco.net.co"
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                // 1. Obtener favoritos desde el API
                val (_, favoritosApi) = apiFavoritoService.obtenerProductosYFavoritosApi(email)
                // 2. Consultar favoritos existentes en la base de datos
                val favoritosExistentes = favoritoDao
                    .obtenerFavoritosConProductosPorEmail(email)
                    .mapNotNull { it.favorito } // Extraer los objetos FavoritoEntity no nulos
                    .map { it.productoId } // Obtener solo los productoId
                    .toSet()
                // 3. Filtrar favoritos nuevos
                val nuevosFavoritos = favoritosApi.filter { it.productoId !in favoritosExistentes }
                // 4. Insertar los favoritos nuevos en la base de datos
                if (nuevosFavoritos.isNotEmpty()) {
                    favoritoDao.insertarFavoritos(nuevosFavoritos)
                }
                // 5. Consultar todos los favoritos con detalles
                val favoritosConDetalles = favoritoDao.obtenerFavoritosConProductosPorEmail(email)
                // 6. Mapear los datos a modelos para el RecyclerView
                val modelosFavoritos = favoritosConDetalles.map { favoritoConDetalle ->
                    ModeloFavorito(
                        nombre = favoritoConDetalle.producto.nombre,
                        precio = "${favoritoConDetalle.producto.precio_oferta}",
                        precioOriginal = "${favoritoConDetalle.producto.precio}",
                        imagen = favoritoConDetalle.producto.url_imagen
                    )
                }
                withContext(Dispatchers.Main) {
                    adaptadorFavorito.actualizarLista(modelosFavoritos)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.d("Error load favorites", "Error: ${e.message}")
                    Toast.makeText(this@MisFavoritosActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}