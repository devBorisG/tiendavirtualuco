package com.example.tiendavirtualuco.persistence.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tiendavirtualuco.persistence.dao.ProductoDao
import com.example.tiendavirtualuco.persistence.entity.ProductoEntity

@Database(entities = [ProductoEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun productoDao(): ProductoDao
}