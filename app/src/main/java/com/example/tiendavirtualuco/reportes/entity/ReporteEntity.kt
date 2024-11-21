package com.example.tiendavirtualuco.reportes.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reportes")
data class ReporteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tipoReporte: String,
    val detalle: String,
    val timestamp: Long)
