package com.example.tiendavirtualuco.trazabilidad

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.trazabilidad.fragmentos.DetalleComprador

class TrazabilidadProductosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trazabilidad_productos)

        // Encuentra el botón
        val btnListadoUsuarios = findViewById<Button>(R.id.BtnListadoUsuarios)


        // Setea el click listener para el botón
        btnListadoUsuarios.setOnClickListener {
            // Cargar el fragmento dinámicamente en el FrameLayout
            val fragment = DetalleComprador() // El fragmento que deseas cargar
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.BtnListadoUsuarios,
                    fragment
                ) // R.id.fragment_container es el FrameLayout
                .addToBackStack(null) // Opción para volver atrás
                .commit()
        }


    }
}