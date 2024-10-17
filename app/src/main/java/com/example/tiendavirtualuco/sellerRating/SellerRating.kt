package com.example.tiendavirtualuco.sellerRating

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendavirtualuco.R

class SellerRating : AppCompatActivity() {

    private lateinit var commentRecyclerView: RecyclerView
    private lateinit var averageRatingBar: RatingBar
    private lateinit var commentAdapter: CommentAdapter
    private val commentsList = mutableListOf<Pair<String, Float>>()
    private val requestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_rating)

        commentRecyclerView = findViewById(R.id.commentRecyclerView)
        averageRatingBar = findViewById(R.id.averageRating)

        commentAdapter = CommentAdapter(commentsList)
        commentRecyclerView.layoutManager = LinearLayoutManager(this)
        commentRecyclerView.adapter = commentAdapter

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
                commentAdapter.notifyDataSetChanged()  // Actualiza el RecyclerView
                updateAverageRating()
            }
        }
    }

    private fun updateAverageRating() {
        if (commentsList.isNotEmpty()) {
            val totalRating = commentsList.sumByDouble { it.second.toDouble() }
            val averageRating = totalRating / commentsList.size
            averageRatingBar.rating = averageRating.toFloat()
        }
    }
}
