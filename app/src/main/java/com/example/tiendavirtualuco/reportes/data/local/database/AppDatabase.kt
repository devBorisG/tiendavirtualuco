package com.example.tiendavirtualuco.reportes.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tiendavirtualuco.reportes.dao.ReporteDao
import com.example.tiendavirtualuco.reportes.dao.TipoReporteDao
import com.example.tiendavirtualuco.reportes.entity.ReporteEntity
import com.example.tiendavirtualuco.reportes.entity.TipoReporteEntity

@Database(entities = [TipoReporteEntity::class, ReporteEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tipoReporteDao(): TipoReporteDao
    abstract fun reporteDao(): ReporteDao
}
