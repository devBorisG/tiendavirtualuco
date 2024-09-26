package com.example.tiendavirtualuco

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivityBuzonNotif : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_buzon_notif)

        // Inicializar RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.rv_buzon_notificaciones)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Lista de notificaciones de prueba
        val notificaciones = listOf("Notificación 1", "Notificación 2", "Notificación 3")

        // Configurar el adaptador
        val adapter = NotificacionesAdapter(notificaciones)
        recyclerView.adapter = adapter
    }
}
