package com.example.tiendavirtualuco.mis_productos.service

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendavirtualuco.MainActivity
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.mis_productos.MisProductosActivity

class AñadirProductoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.anadir_productos)
        configBotonAñadirProducto()
        configBotonAtras()
    }


    fun configBotonAñadirProducto() {
        val btnBack = findViewById<Button>(R.id.BtañadirProductoAñadir)
        btnBack.setOnClickListener {
            val intent = Intent(this, MisProductosActivity::class.java)
            startActivity(intent)
        }
    }


    fun configBotonAtras() {
        val btnBack = findViewById<Button>(R.id.BtDevolverAñadirProducto)
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}