package com.example.tiendavirtualuco

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageButton
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout

class PieView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var imgCompras: ImageView
    private lateinit var imgOfertas: ImageView
    private lateinit var imgProductos: ImageView
    private lateinit var imgHistoricoProductos: ImageView
    private lateinit var imgFavoritos: ImageView

    init {
        inflate(context, R.layout.pie_layout,this)
        imgCompras = findViewById<ImageButton>(R.id.ic_purchase)
        imgOfertas = findViewById<ImageButton>(R.id.ic_search_offers)
        imgProductos = findViewById<ImageButton>(R.id.ic_my_products)
        imgHistoricoProductos = findViewById<ImageButton>(R.id.ic_purchase_history)
        imgFavoritos = findViewById<ImageButton>(R.id.ic_favorites)
    }

}