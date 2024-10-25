package com.example.tiendavirtualuco.trazabilidad.actividades

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.trazabilidad.TrazabilidadProductosActivity
import com.example.tiendavirtualuco.trazabilidad.fragmentos.DetalleComprador
import com.example.tiendavirtualuco.trazabilidad.fragmentos.DetalleVendedor

class TrazabilidadPrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trazabilidad_principal)

        // Encuentra el botón
        val btnSellerDetail = findViewById<Button>(R.id.btnSellerDetail)
        val btnBuyerDetail = findViewById<Button>(R.id.btnBuyerDetail)
        val btnTrazabilidadProductosActivity=findViewById<Button>(R.id.BtnTrazabilidadProductos)

        btnTrazabilidadProductosActivity.setOnClickListener {
            // Inicia la actividad DetalleVendedorActivity
            val intent = Intent(this, TrazabilidadProductosActivity::class.java)
            startActivity(intent)
        }
        btnSellerDetail.setOnClickListener {
            // Cargar el fragmento DetalleVendedor en el FrameLayout (fragment_container)
            val fragment = DetalleVendedor()
            supportFragmentManager.beginTransaction()
                .replace(R.id.btnSellerDetail, fragment)  // fragment_container es el id del FrameLayout
                .addToBackStack(null)  // Para permitir volver atrás
                .commit()
        }
        btnBuyerDetail.setOnClickListener {
            // Cargar el fragmento dinámicamente en el FrameLayout
            val fragment = DetalleComprador() // El fragmento que deseas cargar
            supportFragmentManager.beginTransaction()
                .replace(R.id.btnBuyerDetail, fragment) // R.id.fragment_container es el FrameLayout
                .addToBackStack(null) // Opción para volver atrás
                .commit()
        }

    }
}