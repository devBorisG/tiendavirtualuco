package com.example.tiendavirtualuco

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class DetalleNotificacionActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_notificacion)

        // Obtener el detalle de la notificación desde el Intent
        val detalle = intent.getStringExtra("detalleNotificacion")

        // Mostrar el detalle en el TextView
        val textView = findViewById<TextView>(R.id.tv_detalle_notificacion)
        textView.text = detalle
    }
    // "volver atrás"
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() /* Vuelve a la actividad anterior */
        return true
    }
}
