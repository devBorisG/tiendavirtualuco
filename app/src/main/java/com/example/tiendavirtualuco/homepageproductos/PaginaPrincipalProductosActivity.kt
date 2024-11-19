package com.example.tiendavirtualuco.homepageproductos

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.AlarmManagerCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.homepageproductos.adapter.AdaptadorProducto
import com.example.tiendavirtualuco.homepageproductos.manager.TokenManager
import com.example.tiendavirtualuco.homepageproductos.model.ModeloProducto
import com.example.tiendavirtualuco.homepageproductos.network.RetrofitClient
import com.example.tiendavirtualuco.homepageproductos.repository.AuthRepository
import com.example.tiendavirtualuco.homepageproductos.repository.OfertaRepository
import com.example.tiendavirtualuco.homepageproductos.service.DailyNotificationReceiver
import com.example.tiendavirtualuco.persistence.dao.ProductoDao
import com.example.tiendavirtualuco.persistence.data.local.DatabaseProvider
import com.example.tiendavirtualuco.persistence.entity.ProductoEntity
import com.example.tiendavirtualuco.pie.service.command.settings.CommandManager
import com.example.tiendavirtualuco.pie.service.observe.implementation.LoggingCommandObserver
import kotlinx.coroutines.launch
import java.util.Calendar
import kotlin.math.log

class PaginaPrincipalProductosActivity : AppCompatActivity() {
    private lateinit var productoDao: ProductoDao
    private val CHANNEL_ID = "daily_notification_channel"
    private lateinit var authRepository: AuthRepository
    private lateinit var ofertaRepository: OfertaRepository
    private lateinit var tokenManager: TokenManager
    private lateinit var tvOferta: TextView
    private lateinit var recyclerProductos: RecyclerView
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RetrofitClient.init(this)
        setContentView(R.layout.pagina_principal_productos)
        CommandManager.addObserver(LoggingCommandObserver())
        initRecyclerView()
        initDatabase()
        createNotificationChannel()
        setDailyAlarm()
        // Referenciar vistas
        recyclerProductos = findViewById(R.id.recycler_productos)
        tvOferta = findViewById(R.id.tvOferta)
        progressBar = findViewById(R.id.progressBar)
        // Inicializar repositorios y TokenManager
        authRepository = AuthRepository()
        ofertaRepository = OfertaRepository()
        tokenManager = TokenManager(this)
        // Iniciar autenticación automáticamente
        authenticateAndLoadProductos()
    }

    private fun authenticateAndLoadProductos() {
        lifecycleScope.launch {
            // Mostrar el ProgressBar mientras se realiza la autenticación y la obtención de ofertas
            progressBar.visibility = View.VISIBLE
            tvOferta.visibility = View.GONE
            recyclerProductos.visibility = View.GONE

            try {
                // Autenticación automática
                val authResult = authRepository.authenticate()
                authResult.onSuccess { token ->
                    tokenManager.saveToken(token)
                    // Obtener las ofertas
                    val ofertasResult = ofertaRepository.getOfertas()
                    Log.d("PaginaPrincipalProductosActivity", "ofertasResult: $ofertasResult")
                    ofertasResult.onSuccess { ofertas ->
                        if (ofertas.isNotEmpty()) {
                            // Integrar las ofertas con los productos
                            val productosConOfertas = ProveedorProducto.listaProductos.map { producto ->
                                val oferta = ofertas.find { it.idProducto == producto.id }
                                if (oferta != null){
                                    producto.copy(
                                        precioProducto = "$${oferta.precioOferta}",
                                        oferta = oferta
                                    )
                                }else{
                                    producto
                                }
                            }
                            // Actualizar el adaptador con la lista modificada
                            (recyclerProductos.adapter as AdaptadorProducto).updateProductos(productosConOfertas)
                            recyclerProductos.visibility = View.VISIBLE
                        } else {
                            // No hay ofertas, mostrar todos los productos sin ofertas
                            (recyclerProductos.adapter as AdaptadorProducto).updateProductos(ProveedorProducto.listaProductos)
                            recyclerProductos.visibility = View.VISIBLE
                        }
                    }.onFailure { error ->
                        // Manejar errores al obtener las ofertas
                        tvOferta.text = "Error al obtener las ofertas: ${error.message}"
                        tvOferta.visibility = View.VISIBLE
                    }
                }.onFailure { error ->
                    // Manejar errores de autenticación
                    tvOferta.text = "Error de autenticación: ${error.message}"
                    tvOferta.visibility = View.VISIBLE
                }
            } catch (e: Exception) {
                tvOferta.text = "Ocurrió un error inesperado: ${e.message}"
                tvOferta.visibility = View.VISIBLE
            } finally {
                // Ocultar el ProgressBar después de completar las operaciones
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun initRecyclerView(){
        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler_productos)
        recyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(this, 2)
        recyclerView.adapter = AdaptadorProducto(ProveedorProducto.listaProductos.toMutableList())
    }

    private fun setDailyAlarm() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, DailyNotificationReceiver::class.java).apply {
            action = "com.example.tiendavirtualuco.DAILY_NOTIFICATION"
        }
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        // Configura el tiempo de activación de la notificación a las 12:33 p.m.
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 5)
            set(Calendar.MINUTE, 51)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)

            // Si la hora ya ha pasado hoy, configura la alarma para el día siguiente
            if (before(Calendar.getInstance())) {
                Log.d("DailyNotificationReceiver", "setDailyAlarm before")
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }
        Log.d("DailyNotificationReceiver", "setDailyAlarm CALENDAR: ${calendar.timeInMillis}")
        Log.d("DailyNotificationReceiver", "setDailyAlarm CEL: ${System.currentTimeMillis()}")
        // Usar setExactAndAllowWhileIdle para garantizar la precisión
        AlarmManagerCompat.setExactAndAllowWhileIdle(
            alarmManager,
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )


        //Para probar la notificación en 5 segundos
//        val triggerTime = System.currentTimeMillis() + 5000 // 5 segundos
//        alarmManager.setExact(
//            AlarmManager.RTC_WAKEUP,
//            triggerTime,
//            pendingIntent
//        )
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val channelName = "Daily Notification Channel"
            val channelDescription = "Channel for daily notifications"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(CHANNEL_ID, channelName, importance).apply {
                description = channelDescription
            }
            notificationManager.createNotificationChannel(notificationChannel)
        }
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