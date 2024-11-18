package com.example.tiendavirtualuco

import Product
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
import com.example.tiendavirtualuco.adapter.ProductAdapter
import com.example.tiendavirtualuco.persistence.dao.TrackingDao
import com.example.tiendavirtualuco.persistence.data.local.DatabaseProvider
import com.example.tiendavirtualuco.persistence.entity.TrackingEntity
import kotlinx.coroutines.launch

class TrackingActivity : AppCompatActivity() {

    private lateinit var orderProgressBar: ProgressBar
    private lateinit var recyclerViewProducts: RecyclerView
    private lateinit var trackingDao: TrackingDao
    private lateinit var tvDeliveryAddress: TextView
    private lateinit var step1Circle: View
    private lateinit var step2Circle: View
    private lateinit var step3Circle: View
    private lateinit var step4Circle: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tracking)

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
            //insertarTracksDePrueba()

            // Consultar productos y actualizar la UI
            consultarYActualizarTracks()
        }
    }

    private suspend fun insertarTracksDePrueba() {
        val tracksDePrueba = listOf(
            TrackingEntity(
                status = "Entregado",
                location = "Viva Envigado, ANT",
                timestamp = "2024-11-18T02:20:23+00:00",
            )
        )

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

        // Restablecer todos los círculos a gris
        step1Circle.setBackgroundResource(R.drawable.circle_background_gray)
        step2Circle.setBackgroundResource(R.drawable.circle_background_gray)
        step3Circle.setBackgroundResource(R.drawable.circle_background_gray)
        step4Circle.setBackgroundResource(R.drawable.circle_background_gray)

        // Cambiar color según el estado
        when (status) {
            "Despachado" -> {
                step1Circle.setBackgroundResource(R.drawable.circle_background_green)
                step2Circle.setBackgroundResource(R.drawable.circle_background_green)
            }
            "En camino" -> {
                step1Circle.setBackgroundResource(R.drawable.circle_background_green)
                step2Circle.setBackgroundResource(R.drawable.circle_background_green)
                step3Circle.setBackgroundResource(R.drawable.circle_background_green)
            }
            "Entregado" -> {
                step1Circle.setBackgroundResource(R.drawable.circle_background_green)
                step2Circle.setBackgroundResource(R.drawable.circle_background_green)
                step3Circle.setBackgroundResource(R.drawable.circle_background_green)
                step4Circle.setBackgroundResource(R.drawable.circle_background_green)
            }
            else -> {
                step1Circle.setBackgroundResource(R.drawable.circle_background_gray)
                step2Circle.setBackgroundResource(R.drawable.circle_background_gray)
                step3Circle.setBackgroundResource(R.drawable.circle_background_gray)
                step4Circle.setBackgroundResource(R.drawable.circle_background_gray)
            }
        }
    }

}