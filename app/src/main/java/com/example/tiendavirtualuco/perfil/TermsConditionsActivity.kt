package com.example.tiendavirtualuco.perfil

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendavirtualuco.R

class TermsConditionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms)

        val btnAcceptTerms = findViewById<Button>(R.id.btn_accept_terms)
        val btnBack = findViewById<ImageButton>(R.id.btn_back)

        btnAcceptTerms.setOnClickListener {
            // Mostrar un mensaje de aceptación
            Toast.makeText(this, "Términos y condiciones aceptados", Toast.LENGTH_SHORT).show()

            // Cambiar el texto del botón y deshabilitarlo
            btnAcceptTerms.text = "Términos Aceptados"
            btnAcceptTerms.isEnabled = false
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}
