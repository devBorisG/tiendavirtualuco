package com.example.tiendavirtualuco.reportes.data.local

import android.content.Context
import androidx.room.Room
import com.example.tiendavirtualuco.reportes.data.local.database.AppDatabase

object DataBase {
    private var database: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        if (database == null) {
            // Inicializar la base de datos con Room
            database = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, // Tu clase de base de datos
                "app_database"           // El nombre de la base de datos
            ).fallbackToDestructiveMigration() // Manejador de migraciones (si es necesario)
                .build()
        }
        return database!!
    }
}
