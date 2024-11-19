package com.example.tiendavirtualuco.tracking

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.persistence.dao.TrackingDao
import com.example.tiendavirtualuco.persistence.data.local.DatabaseProvider
import com.example.tiendavirtualuco.persistence.entity.TrackingEntity
import kotlinx.coroutines.launch
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager // <- Agregar esta importación
import android.os.Build
import androidx.core.app.AlarmManagerCompat
import androidx.core.app.NotificationCompat
import com.example.tiendavirtualuco.tracking.ProductAdapter
import com.example.tiendavirtualuco.tracking.manager.TokenManager
import com.example.tiendavirtualuco.tracking.network.RetrofitClient
import com.example.tiendavirtualuco.tracking.repository.AuthRepository
import com.example.tiendavirtualuco.tracking.repository.TrackRepository
import com.example.tiendavirtualuco.tracking.service.DailyNotificationReceiver
import com.example.tiendavirtualuco.pie.service.command.settings.CommandManager
import com.example.tiendavirtualuco.pie.service.observe.implementation.LoggingCommandObserver
import java.util.Calendar

class TrackingActivity : AppCompatActivity() {

    private lateinit var orderProgressBar: ProgressBar
    private lateinit var recyclerViewProducts: RecyclerView
    private lateinit var trackingDao: TrackingDao
    private lateinit var tvDeliveryAddress: TextView
    private lateinit var step1Circle: View
    private lateinit var step2Circle: View
    private lateinit var step3Circle: View
    private lateinit var step4Circle: View
    private val CHANNEL_ID = "daily_notification_channel"
    private lateinit var authRepository: AuthRepository
    private lateinit var trackRepository: TrackRepository
    private lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RetrofitClient.init(this)
        setContentView(R.layout.tracking)
        CommandManager.addObserver(LoggingCommandObserver())

        createNotificationChannel()
        setDailyAlarm()

        // Verificar permisos de notificación (Android 13+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 101)
            }
        }

        // Inicializar repositorios y TokenManager
        authRepository = AuthRepository()
        trackRepository = TrackRepository()
        tokenManager = TokenManager(this)

        // Iniciar autenticación automáticamente
        authenticateAndLoadTracks()

        // Inicializar views
        orderProgressBar = findViewById(R.id.progressBar)
        recyclerViewProducts = findViewById(R.id.recyclerViewProducts)
        tvDeliveryAddress = findViewById(R.id.tvDeliveryAddress)
        step1Circle = findViewById(R.id.step1Circle)
        step2Circle = findViewById(R.id.step2Circle)
        step3Circle = findViewById(R.id.step3Circle)
        step4Circle = findViewById(R.id.step4Circle)

        // Crear lista de productos
        val products = listOf(
            Product("Coca-Cola", "$5000 COP", R.drawable.sample_image),
            Product("Pepsi", "$4500 COP", R.drawable.pepsiimage),
            Product("Sprite", "$4800 COP", R.drawable.spriteimage),
            Product("Fanta", "$4900 COP", R.drawable.fanta)
        )

        // Configurar el adaptador del RecyclerView
        val adapter = ProductAdapter(this, products) { product ->
            showProductDetails(product)
        }
        recyclerViewProducts.layoutManager = LinearLayoutManager(this)
        recyclerViewProducts.adapter = adapter

        // Configurar botón para reportar problemas
        val btnReportIssue = findViewById<Button>(R.id.btnReportIssue)
        btnReportIssue.setOnClickListener {
            showReportIssueDialog()
        }

        initDatabase()
        showNotification("Bienvenido", "Tu actividad de tracking está lista.")
    }

    private fun authenticateAndLoadTracks() {
        lifecycleScope.launch {
            try {
                // Autenticación automática
                val authResult = authRepository.authenticate()

                authResult.onSuccess { token ->
                    tokenManager.saveToken(token)

                    // Obtener los tracks
                    val trackResult = trackRepository.getTracks()
                    trackResult.onSuccess { tracks ->
                        Log.d("TrackingActivity", "Tracks obtenidos: $tracks")

                        // Guardar tracks en la base de datos
                        trackingDao.insertTrack(tracks.map {
                            TrackingEntity(
                                idTrack = it.idTrack,
                                status = it.status,
                                location = it.location,
                                timestamp = it.timestamp
                            )
                        })

                        // Actualizar UI
                        consultarYActualizarTracks()
                    }.onFailure { error ->
                        mostrarMensajeError("Error al cargar los tracks: ${error.message}")
                    }
                }.onFailure { error ->
                    mostrarMensajeError("Error de autenticación: ${error.message}")
                }
            } catch (e: Exception) {
                mostrarMensajeError("Ocurrió un error inesperado: ${e.message}")
            }
        }
    }

    // Mostrar mensajes de error en la UI
    private fun mostrarMensajeError(mensaje: String) {
        runOnUiThread {
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
            Log.e("TrackingActivity", mensaje)
        }
    }

    private fun showProductDetails(product: Product) {
        Toast.makeText(this, "Seleccionaste: ${product.name}", Toast.LENGTH_SHORT).show()
    }

    private fun showReportIssueDialog() {
        val options = arrayOf("Mi pedido no llega", "Error en mi pago", "Cómo cancelo mi compra", "Otra")

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Seleccione una opción")
        builder.setItems(options) { dialog, which ->
            showSupportMessage() // Mostrar mensaje de soporte independientemente de la opción seleccionada
        }

        builder.show()
    }

    private fun showSupportMessage() {
        val message = "Un asesor se comunicará contigo 🤖📞"
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun initDatabase() {
        val db = DatabaseProvider.getDatabase(this)
        trackingDao = db.trackingDao()

        // Lanzar una corrutina para operaciones de base de datos
        lifecycleScope.launch {
            // Insertar productos de prueba
            insertarTracksDePrueba()

            // Consultar productos y actualizar la UI
            consultarYActualizarTracks()
        }
    }

    private suspend fun insertarTracksDePrueba() {
        // Definir los datos de prueba
        trackingDao.deleteTrackById(5)

        val tracksDePrueba = listOf(
            TrackingEntity(
                idTrack = 2,
                status = "Por confirmar",
                location = "Miami, USA",
                timestamp = "2024-11-18T02:20:23+00:00",
            ),
            TrackingEntity(
                idTrack = 2,
                status = "Despachado",
                location = "Medellin, COL",
                timestamp = "2024-11-18T02:30:00+00:00"
            ),
            TrackingEntity(
                idTrack = 2,
                status = "Entregado",
                location = "Rionegro, COL",
                timestamp = "2024-11-18T02:50:00+00:00"
            )
        )

        // Insertar los datos, reemplazando si ya existen
        trackingDao.insertTrack(tracksDePrueba)
    }

    private suspend fun consultarYActualizarTracks() {
        val listaTracks = trackingDao.getAllTracks()

        // Imprimir los productos en Logcat
        listaTracks.forEach { track ->
            Log.d("Track", "idTrack: ${track.idTrack}, Status: ${track.status}, Location: ${track.location}, Timestamp: ${track.timestamp}")

            // Actualizar el progreso de la barra según el estado
            val progress = when (track.status) {
                "Despachado" -> 33
                "En camino" -> 66
                "Entregado" -> 100
                else -> 0
            }

            val texto = "Lugar: ${track.location}\nFecha de compra: ${track.timestamp}"

            // Actualizar la barra de progreso en el hilo principal
            runOnUiThread {
                orderProgressBar.progress = progress
                tvDeliveryAddress.text = texto

                actualizarColoresPasos(track.status)
            }
        }
    }

    private fun actualizarColoresPasos(status: String) {
        // Círculos de cada paso
        val step1Circle = findViewById<View>(R.id.step1Circle)
        val step2Circle = findViewById<View>(R.id.step2Circle)
        val step3Circle = findViewById<View>(R.id.step3Circle)
        val step4Circle = findViewById<View>(R.id.step4Circle)

        // Establecer el color predeterminado para cada paso
        step1Circle.setBackgroundResource(R.drawable.circle_background_gray)
        step2Circle.setBackgroundResource(R.drawable.circle_background_gray)
        step3Circle.setBackgroundResource(R.drawable.circle_background_gray)
        step4Circle.setBackgroundResource(R.drawable.circle_background_gray)

        // Actualizar los círculos según el estado
        when (status) {
            "Despachado" -> step1Circle.setBackgroundResource(R.drawable.circle_background_green)
            "En camino" -> {
                step1Circle.setBackgroundResource(R.drawable.circle_background_green)
                step2Circle.setBackgroundResource(R.drawable.circle_background_green)
            }
            "Entregado" -> {
                step1Circle.setBackgroundResource(R.drawable.circle_background_green)
                step2Circle.setBackgroundResource(R.drawable.circle_background_green)
                step3Circle.setBackgroundResource(R.drawable.circle_background_green)
                step4Circle.setBackgroundResource(R.drawable.circle_background_green)
            }
        }
    }

    // Crear un canal de notificación
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Daily Notifications"
            val descriptionText = "Channel for daily notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    // Mostrar una notificación
    private fun showNotification(title: String, content: String) {
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }

    private fun setDailyAlarm() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, DailyNotificationReceiver::class.java)

        // Establecer FLAG_IMMUTABLE o FLAG_MUTABLE según corresponda
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE // Cambiar aquí para que sea inmutable
        )

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 9)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }

        if (calendar.timeInMillis < System.currentTimeMillis()) {
            calendar.add(Calendar.DATE, 1) // Si ya pasó la hora, configura para el siguiente día
        }

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    }
}