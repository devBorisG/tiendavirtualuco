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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.AlarmManagerCompat
import androidx.lifecycle.lifecycleScope
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.homepageproductos.adapter.AdaptadorProducto
import com.example.tiendavirtualuco.homepageproductos.service.DailyNotificationReceiver
import com.example.tiendavirtualuco.persistence.dao.ProductoDao
import com.example.tiendavirtualuco.persistence.data.local.DatabaseProvider
import com.example.tiendavirtualuco.persistence.entity.ProductoEntity
import com.example.tiendavirtualuco.pie.service.command.settings.CommandManager
import com.example.tiendavirtualuco.pie.service.observe.implementation.LoggingCommandObserver
import kotlinx.coroutines.launch
import java.util.Calendar

class PaginaPrincipalProductosActivity : AppCompatActivity() {
    private lateinit var productoDao: ProductoDao
    private val CHANNEL_ID = "daily_notification_channel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagina_principal_productos)
        CommandManager.addObserver(LoggingCommandObserver())
        initRecyclerView()
        initDatabase()
        createNotificationChannel()
        setDailyAlarm()
    }

    private fun initRecyclerView(){
        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler_productos)
        recyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(this, 2)
        recyclerView.adapter = AdaptadorProducto(ProveedorProducto.listaProductos)
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