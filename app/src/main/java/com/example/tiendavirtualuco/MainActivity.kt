package com.example.tiendavirtualuco

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button;
import com.example.tiendavirtualuco.mis_productos.AñadirProductoActivity

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.mis_productos_vacio)
        configBotonAtras()

    }


    fun configBotonAtras(){
        val btnBack = findViewById<Button>(R.id.btnAñadirProductoDesdeVacio)
        btnBack.setOnClickListener {
            val intent = Intent(this, AñadirProductoActivity ::class.java)
            startActivity(intent)
        }
    }


}