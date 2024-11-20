package com.example.tiendavirtualuco.sellerRating

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tiendavirtualuco.R

class Review_seller : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_seller)

        val sendReviewButton = findViewById<Button>(R.id.send_Review)
        val commentEditText = findViewById<EditText>(R.id.comment)
        val ratingBar = findViewById<RatingBar>(R.id.reviewRating)

        sendReviewButton.setOnClickListener {
            val comment = commentEditText.text.toString()
            val rating = ratingBar.rating

            if (comment.isNotEmpty()) {
                val resultIntent = Intent()
                resultIntent.putExtra("comment", comment)
                resultIntent.putExtra("rating", rating)

                setResult(RESULT_OK, resultIntent)
                Toast.makeText(this, "Comentario enviado", Toast.LENGTH_SHORT).show()

                finish() // Cierra la actividad y regresa
            } else {
                Toast.makeText(this, "Por favor escribe un comentario", Toast.LENGTH_SHORT).show()
            }
        }

    }
}