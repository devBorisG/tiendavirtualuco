package com.example.tiendavirtualuco.persistence.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tiendavirtualuco.persistence.dao.FavoritoDao
import com.example.tiendavirtualuco.persistence.dao.ProductoDao
import com.example.tiendavirtualuco.persistence.entity.FavoritoEntity
import com.example.tiendavirtualuco.persistence.entity.ProductoEntity

@Database(entities = [ProductoEntity::class, FavoritoEntity::class], version = 5)
abstract class AppDatabase: RoomDatabase() {
    abstract fun productoDao(): ProductoDao
    abstract fun favoritoDao(): FavoritoDao
}