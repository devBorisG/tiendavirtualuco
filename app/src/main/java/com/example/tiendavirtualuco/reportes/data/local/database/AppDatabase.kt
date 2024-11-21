package com.example.tiendavirtualuco.reportes.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tiendavirtualuco.reportes.dao.TipoReporteDao
import com.example.tiendavirtualuco.reportes.entity.TipoReporteEntity

@Database(entities = [TipoReporteEntity::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tipoReporteDao(): TipoReporteDao
}
