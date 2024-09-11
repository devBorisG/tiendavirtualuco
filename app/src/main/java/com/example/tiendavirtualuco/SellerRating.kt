package com.example.tiendavirtualuco

import android.os.Bundle
import android.widget.RatingBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tiendavirtualuco.sellerRating.Seller

class SellerRating : AppCompatActivity() {
    val seller = listOf(
        Seller("BJCastro", "Septiembre 20", 3, "me gusta mucho")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seller_rating)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val rBar = findViewById<RatingBar>(R.id.averageRating)
        rBar.rating = 4.5f
    }
}