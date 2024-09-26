package com.example.tiendavirtualuco

import Product
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendavirtualuco.adapter.ProductAdapter

class TrackingActivity : AppCompatActivity() {

    private lateinit var orderProgressBar: ProgressBar
    private lateinit var recyclerViewProducts: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tracking)

        // Inicializar views
        orderProgressBar = findViewById(R.id.progressBar)
        recyclerViewProducts = findViewById(R.id.recyclerViewProducts)

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

        // Configurar la barra de progreso
        orderProgressBar.progress = 75

        // Configurar botón para reportar problemas
        val btnReportIssue = findViewById<Button>(R.id.btnReportIssue)
        btnReportIssue.setOnClickListener {
            showReportIssueDialog()
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

    private fun updateProgressBar(step: Int) {
        orderProgressBar.progress = step
    }

    private fun showSupportMessage() {
        val message = "Un asesor se comunicará contigo 🤖📞"
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}