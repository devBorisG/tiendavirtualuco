package com.example.tiendavirtualuco.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracking")
data class TrackingEntity(
    @PrimaryKey(autoGenerate = true) val idTrack: Int = 0,
    val status: String,
    val location: String,
    val timestamp: String,
)