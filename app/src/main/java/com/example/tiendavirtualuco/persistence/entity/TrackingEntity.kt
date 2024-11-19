package com.example.tiendavirtualuco.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracking")
data class TrackingEntity(
    @PrimaryKey(autoGenerate = true) val primaryKey: Int = 0, // Clave primaria única generada por Room
    val idTrack: Int, // Puede repetirse
    val status: String,
    val location: String,
    val timestamp: String
)