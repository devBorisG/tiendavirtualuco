package com.example.tiendavirtualuco.sellerRating

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendavirtualuco.R

class SellerRating : AppCompatActivity() {

    private lateinit var commentContainer: LinearLayout
    private lateinit var averageRatingBar: RatingBar
    private val requestCode = 1
    private val commentsList = mutableListOf<Pair<String, Float>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_rating)
        commentContainer = findViewById(R.id.commentContainer)
        averageRatingBar = findViewById(R.id.averageRating)

        val writeReviewButton = findViewById<Button>(R.id.writeReviewButton)
        writeReviewButton.setOnClickListener {
            val intent = Intent(this, Review_seller::class.java)
            startActivityForResult(intent, requestCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == this.requestCode && resultCode == RESULT_OK) {
            val comment = data?.getStringExtra("comment")
            val rating = data?.getFloatExtra("rating", 0f)

            if (comment != null && rating != null) {
                commentsList.add(Pair(comment, rating))
                updateCommentsUI()
                updateAverageRating()
            }
        }
    }

    private fun updateCommentsUI() {
        commentContainer.removeAllViews()

        for ((comment, rating) in commentsList) {
            val commentTextView = TextView(this).apply {
                text = "Comentario: $comment \nCalificación: $rating"
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 16, 0, 16)
                }
                textSize = 16f
            }
            commentContainer.addView(commentTextView)
        }
    }

    private fun updateAverageRating() {
        if (commentsList.isNotEmpty()) {
            val totalRating = commentsList.sumByDouble { it.second.toDouble() }
            val averageRating = totalRating / commentsList.size
            averageRatingBar.rating = averageRating.toFloat()
        }
    }

    override fun onResume() {
        super.onResume()
        updateCommentsUI()
    }
}