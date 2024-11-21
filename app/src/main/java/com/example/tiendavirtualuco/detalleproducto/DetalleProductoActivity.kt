package com.example.tiendavirtualuco.detalleproducto

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.homepageproductos.PaginaPrincipalProductosActivity
import com.example.tiendavirtualuco.tienda.MainTiendaActivity

class DetalleProductoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalle_producto)
        // Recuperar el ModeloDetalleProducto enviado desde la actividad principal
        val detalleProducto = intent.getSerializableExtra("detalleProducto") as? ModeloDetalleProducto
        if (detalleProducto != null) {
            mostrarDetalleProducto(detalleProducto)
        }
        configBotonAtras()
        configBotonVisitar()
    }

    private fun mostrarDetalleProducto(producto: ModeloDetalleProducto) {
        val imgProducto = findViewById<ImageView>(R.id.imgProducto)
        val tvNombreProducto = findViewById<TextView>(R.id.tvTituloProducto)
        val tvPrecioProducto = findViewById<TextView>(R.id.tvPrecioProducto)
        val tvPrecioOriginal = findViewById<TextView>(R.id.tvPrecioOriginal)
        val tvDescripcionProducto = findViewById<TextView>(R.id.tvDescripcionProducto)
        val tvNombreTienda = findViewById<TextView>(R.id.tvNombreTienda)
        // Configurar las vistas con los datos del producto
        tvNombreProducto.text = producto.nombre
        tvPrecioProducto.text = producto.precio
        tvDescripcionProducto.text = producto.descripcion
        tvNombreTienda.text = producto.nombreTienda

        if (producto.precioOriginal != "null") {
            tvPrecioOriginal.text = producto.precioOriginal
            tvPrecioOriginal.visibility = View.VISIBLE
        } else {
            tvPrecioOriginal.visibility = View.GONE
        }

        Glide.with(this)
            .load(producto.imagen)
            .into(imgProducto)
    }

    private fun configBotonAtras() {
        val btnBack = findViewById<ImageView>(R.id.btnAtras)
        btnBack.setOnClickListener {
            finish()
        }
    }
    private fun configBotonVisitar() {
        val btnVisitar = findViewById<Button>(R.id.btnVisitar)
        btnVisitar.setOnClickListener {
            val intent = Intent(this, MainTiendaActivity::class.java)
            startActivity(intent)
        }
    }
}