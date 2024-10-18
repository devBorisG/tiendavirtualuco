package com.example.tiendavirtualuco.homepageproductos

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.favoritos.MisFavoritosActivity
import com.example.tiendavirtualuco.homepageproductos.adapter.AdaptadorProducto
import com.example.tiendavirtualuco.persistence.dao.ProductoDao
import com.example.tiendavirtualuco.persistence.data.local.DatabaseProvider
import com.example.tiendavirtualuco.persistence.entity.ProductoEntity
import com.example.tiendavirtualuco.pie.service.command.CommandsViewsEnum
import com.example.tiendavirtualuco.pie.service.command.settings.CommandFactory
import com.example.tiendavirtualuco.pie.service.command.settings.CommandManager
import com.example.tiendavirtualuco.pie.service.observe.implementation.LoggingCommandObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class PaginaPrincipalProductosActivity : AppCompatActivity() {
    private lateinit var productoDao: ProductoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagina_principal_productos)
        CommandManager.addObserver(LoggingCommandObserver())
        initRecyclerView()
        initDatabase()
    }

    private fun initRecyclerView(){
        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler_productos)
        recyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(this, 2)
        recyclerView.adapter = AdaptadorProducto(ProveedorProducto.listaProductos)
    }

    private fun initDatabase() {
        val db = DatabaseProvider.getDatabase(this)
        productoDao = db.productoDao()

        // Lanzar una corrutina para operaciones de base de datos
        lifecycleScope.launch {
            // Insertar productos de prueba
            insertarProductosDePrueba()

            // Consultar productos y actualizar la UI
            consultarYActualizarProductos()
        }
    }

    private suspend fun insertarProductosDePrueba() {
        val productosDePrueba = listOf(
            ProductoEntity(
                nombre = "Producto A",
                cantidad = 10,
                precio = 100.0,
                descripcion = "Descripción del Producto A",
                url_imagen = "https://www.ejemplo.com/imagenA.jpg",
                es_oferta = false,
                precio_oferta = 0.0,
                precio_envio = 10.0
            ),
            ProductoEntity(
                nombre = "Producto B",
                cantidad = 5,
                precio = 50.0,
                descripcion = "Descripción del Producto B",
                url_imagen = "https://www.ejemplo.com/imagenB.jpg",
                es_oferta = true,
                precio_oferta = 45.0,
                precio_envio = 5.0
            ),
            // Agrega más productos si lo deseas
        )

        // Insertar productos en la base de datos
        productoDao.insertProductos(productosDePrueba)
    }

    private suspend fun consultarYActualizarProductos() {
        // Consultar todos los productos
        // Consultar todos los productos
        val listaProductos = productoDao.getAllProductos()

        // Imprimir los productos en Logcat
        listaProductos.forEach { producto ->
            Log.d("Producto", "ID: ${producto.id}, Nombre: ${producto.nombre}")
        }
    }

}