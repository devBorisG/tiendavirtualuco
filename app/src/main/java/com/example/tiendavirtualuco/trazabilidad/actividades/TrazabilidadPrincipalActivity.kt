package com.example.tiendavirtualuco.trazabilidad.actividades

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.trazabilidad.fragmentos.DetalleComprador

class TrazabilidadPrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trazabilidad_principal)

        // Encuentra el botón
        val btnSellerDetail = findViewById<Button>(R.id.btnSellerDetail)
        val btnBuyerDetail = findViewById<Button>(R.id.btnBuyerDetail)


        // Setea el click listener para el botón
        btnSellerDetail.setOnClickListener {
            // Cargar el fragmento dinámicamente en el FrameLayout
            val fragment = DetalleComprador() // El fragmento que deseas cargar
            supportFragmentManager.beginTransaction()
                .replace(R.id.btnSellerDetail, fragment) // R.id.fragment_container es el FrameLayout
                .addToBackStack(null) // Opción para volver atrás
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