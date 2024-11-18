package com.example.tiendavirtualuco.persistence.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tiendavirtualuco.persistence.dao.ProductoDao
import com.example.tiendavirtualuco.persistence.dao.TrackingDao
import com.example.tiendavirtualuco.persistence.entity.ProductoEntity
import com.example.tiendavirtualuco.persistence.entity.TrackingEntity

@Database(entities = [ProductoEntity::class,TrackingEntity::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun productoDao(): ProductoDao
    abstract fun trackingDao(): TrackingDao
}