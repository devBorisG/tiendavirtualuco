package com.example.tiendavirtualuco.tienda

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.tienda.model.ModeloTienda
import com.example.tiendavirtualuco.tienda.network.RetrofitClient
import com.example.tiendavirtualuco.tienda.repository.TiendaRepository
import kotlinx.coroutines.launch

class DetallesTiendaActivity : AppCompatActivity() {

    private val tiendaRepository = TiendaRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalles_tienda)
        RetrofitClient.init(this)

        val idTienda = intent.getIntExtra("ID_TIENDA", -1)
        if (idTienda == -1) {
            Toast.makeText(this, "Error al obtener el ID de la tienda", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            obtenerDetallesTienda(idTienda)
        }
    }

    private fun obtenerDetallesTienda(idTienda: Int) {

        lifecycleScope.launch {
            val resultado = tiendaRepository.getDetallesTienda(idTienda)
            resultado.onSuccess { tienda ->
                mostrarDetallesTienda(tienda)
            }.onFailure { error ->
                Toast.makeText(this@DetallesTiendaActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun mostrarDetallesTienda(tienda: ModeloTienda) {
        findViewById<TextView>(R.id.nombreTienda).text = tienda.nombre
        findViewById<TextView>(R.id.webAddress).text = tienda.webAddress
        findViewById<TextView>(R.id.descripcionTienda).text = tienda.descripcion
        findViewById<TextView>(R.id.tipoTienda).text = tienda.tipo
        findViewById<TextView>(R.id.direccionTienda).text = tienda.direccion
        findViewById<TextView>(R.id.ciudadTienda).text = tienda.ciudad
        findViewById<TextView>(R.id.paisTienda).text = tienda.pais
        findViewById<TextView>(R.id.nombreCourier).text = tienda.nombreCourier
    }
}
