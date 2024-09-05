package com.example.tiendavirtualuco.detalleproducto

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.tiendavirtualuco.R

class DetalleProductoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalle_producto)
        setImage()
    }

    fun setImage(){
        val imgProducto = findViewById<ImageView>(R.id.imgProducto)
        Glide.with(this)
            .load("https://http2.mlstatic.com/D_NQ_NP_2X_629321-MLA52579227077_112022-F.webp")
            .override(300,140)
            .centerCrop()
            .into(imgProducto)
    }
}