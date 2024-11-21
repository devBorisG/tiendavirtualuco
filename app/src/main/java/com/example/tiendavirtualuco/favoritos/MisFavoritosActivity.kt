package com.example.tiendavirtualuco.favoritos

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.favoritos.adapter.AdaptadorFavorito
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
    val channelId = "favorito_notification_channel" // Debe coincidir


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RetrofitClient.init(this)
        setContentView(R.layout.mis_favoritos)
        val database = DatabaseProvider.getDatabase(this)
        productoDao = database.productoDao()
        favoritoDao = database.favoritoDao()
        favoritoClient = FavoritoServiceClient()
        crearCanalNotificaciones()
        initRecyclerView()
        cargarFavoritos()
    }
    /**
     * Inicializa el RecyclerView y el Adaptador
     */
    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_favoritos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adaptadorFavorito = AdaptadorFavorito(mutableListOf()) { favorito ->
            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    // Eliminar favorito de la base de datos
                    favoritoDao.eliminarFavoritoPorId(favorito.id)
                    // Enviar notificación
                    enviarNotificacionFavorito(favorito.nombre)
                    crearConfirmacion(favorito.nombre)
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@MisFavoritosActivity,
                            "Error al eliminar favorito: ${e.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
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
                val modelosFavoritos = favoritosConDetalles.mapNotNull { favoritoConDetalle ->
                    favoritoConDetalle.favorito?.let { favorito ->
                        ModeloFavorito(
                            id = favorito.id,
                            nombre = favoritoConDetalle.producto.nombre,
                            precio = "${favoritoConDetalle.producto.precio_oferta ?: "No disponible"}",
                            precioOriginal = "${favoritoConDetalle.producto.precio ?: "No disponible"}",
                            imagen = favoritoConDetalle.producto.url_imagen ?: ""
                        )
                    }
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

    private fun enviarNotificacionFavorito(nombreFavorito: String) {
        Log.d("NotificationDebug", "Iniciando la creación de la notificación para: $nombreFavorito")

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = System.currentTimeMillis().toInt()

        val intent = Intent(this, MisFavoritosActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder(this, "favorito_notification_channel")
        } else {
            NotificationCompat.Builder(this)
        }.apply {
            setSmallIcon(R.drawable.ic_notification) // Verifica que este ícono existe
            setContentTitle("Favorito eliminado")
            setContentText("$nombreFavorito ha sido eliminado de tus favoritos.")
            setPriority(NotificationCompat.PRIORITY_HIGH)
            setContentIntent(pendingIntent)
            setAutoCancel(true)
        }.build()
        Log.d("NotificationDebug", "Notificación creada, enviando...")
        notificationManager.notify(notificationId, notification)
        Log.d("NotificationDebug", "Notificación enviada con ID: $notificationId")
    }


    private fun crearCanalNotificaciones() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = channelId
            val channelName = "Notificaciones de Favoritos"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = "Notificaciones cuando se eliminan favoritos"
            }
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            Log.d("NotificationDebug", "Canal de notificación creado: $channelId")
        }
    }

    private suspend fun crearConfirmacion(favorito: String) {
        withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@MisFavoritosActivity,
                            "${favorito} ha sido notificado sobre su eliminación en favoritos.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
    }
}