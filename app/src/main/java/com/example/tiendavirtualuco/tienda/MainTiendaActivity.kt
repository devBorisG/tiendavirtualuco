package com.example.tiendavirtualuco.tienda

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendavirtualuco.R

class MainTiendaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_tienda)
        botonCrearTienda()
    }

    fun botonCrearTienda(){
        val btnBack = findViewById<Button>(R.id.createStoreButton)
        btnBack.setOnClickListener {
            val intent = Intent(this, CrearTiendaActivity::class.java)
            startActivity(intent)
        }
    }
}