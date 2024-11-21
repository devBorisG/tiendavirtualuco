package com.example.tiendavirtualuco.reportes.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tiendavirtualuco.reportes.entity.TipoReporteEntity

@Dao

interface TipoReporteDao {

    @Query("SELECT * FROM tiporeporte")
    suspend fun getAllOptions(): List<TipoReporteEntity>

    @Insert
    suspend fun insertOption(tipoReporte: List<TipoReporteEntity>)

    @Query("DELETE FROM tiporeporte")
    suspend fun clearTable()
}