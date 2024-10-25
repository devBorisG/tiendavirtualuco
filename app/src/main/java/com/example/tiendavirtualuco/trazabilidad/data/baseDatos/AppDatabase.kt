package com.example.tiendavirtualuco.trazabilidad.data.baseDatos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tiendavirtualuco.persistence.dao.ProductoDao
import com.example.tiendavirtualuco.trazabilidad.dao.ProductoDAO
import com.example.tiendavirtualuco.trazabilidad.entidad.ProductoEntity

@Database(entities = [ProductoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productoDao(): ProductoDAO

    /*companion object {
        private const val DATABASE_NAME = "pmovil.db"

        private lateinit var instance: AppDatabase

        fun getInstance(context: Context?): AppDatabase {
            if (Companion::instance.isInitialized) {
                return instance
            } else {
                instance = Room.databaseBuilder(
                    context!!.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .allowMainThreadQueries()
                    .build()

                return instance
            }

        }
    }*/

    //abstract fun productDAO(): ProductoDAO

}
