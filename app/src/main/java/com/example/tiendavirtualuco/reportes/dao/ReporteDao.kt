package com.example.tiendavirtualuco.reportes.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tiendavirtualuco.reportes.entity.ReporteEntity

@Dao
interface ReporteDao {
    @Insert
    suspend fun insertReporte(reporte: ReporteEntity)

    @Query("SELECT * FROM reportes ORDER BY timestamp DESC")
    suspend fun getAllReportes(): List<ReporteEntity>
}