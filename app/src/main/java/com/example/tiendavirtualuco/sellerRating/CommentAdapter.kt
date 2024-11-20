package com.example.tiendavirtualuco.sellerRating

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendavirtualuco.R

class CommentAdapter(private val comments: List<Pair<String, Float>>) :
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val commentText: TextView = view.findViewById(R.id.commentText)
        val commentRatingBar: RatingBar = view.findViewById(R.id.commentRatingBar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val (comment, rating) = comments[position]
        holder.commentText.text = comment
        holder.commentRatingBar.rating = rating
    }

    override fun getItemCount(): Int {
        return comments.size
    }
}
