package com.example.tiendavirtualuco

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button;
import android.widget.ImageView
import com.example.tiendavirtualuco.mis_productos.PaginaPrincipalProductosActivity

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.mis_productos_vacio)
    }
    @SuppressLint("WrongViewCast")
    fun configBotonAtras(){
        val btnBack = findViewById<ImageView>(R.id.btnAñadirProductoDesdeVacio)
        btnBack.setOnClickListener {
            val intent = Intent(this, PaginaPrincipalProductosActivity::class.java)
            startActivity(intent)
        }
    }

}