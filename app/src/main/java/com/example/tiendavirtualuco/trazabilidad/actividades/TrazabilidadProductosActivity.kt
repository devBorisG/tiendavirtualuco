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
        val btnListadoUsuarios: Button = findViewById(R.id.BtnListadoUsuarios)

        // Setea el click listener para el botón
        btnListadoUsuarios.setOnClickListener {
            cargarFragmento()
        }
    }

    private fun cargarFragmento() {
        val fragment = DetalleComprador() // El fragmento que deseas cargar
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.btnProductDetail, // Asegúrate de usar el ID correcto del FrameLayout
                fragment
            )
            .addToBackStack("Volver") // Opción para volver atrás
            .commit()
    }
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}
