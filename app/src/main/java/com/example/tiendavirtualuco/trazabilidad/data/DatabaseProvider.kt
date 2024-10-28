package com.example.tiendavirtualuco.trazabilidad.data

import android.content.Context
import androidx.room.Room
import com.example.tiendavirtualuco.trazabilidad.data.baseDatos.AppDatabase


object DatabaseProvider {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "tienda_virtual_uco.db"
            ).fallbackToDestructiveMigration().build()
            INSTANCE = instance
            instance
        }
    }
}