package com.example.tiendavirtualuco.reportes.data.local

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.tiendavirtualuco.reportes.data.local.database.AppDatabase
import java.io.File

object DataBase {
    private var database: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        if (database == null) {
            // Inicializar la base de datos con Room y habilitar logs
            database = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, // Tu clase de base de datos
                "app_database"           // El nombre de la base de datos
            )
                .fallbackToDestructiveMigration() // Manejador de migraciones (si es necesario)
                .setQueryCallback({ sqlQuery, bindArgs ->
                    Log.d("RoomQuery", "Consulta ejecutada: $sqlQuery con argumentos: $bindArgs")
                }, java.util.concurrent.Executors.newSingleThreadExecutor())
                .build()
            Log.d("Database", "La base de datos se ha inicializado correctamente.")
        }
        return database!!
    }

    /**
     * Método para exportar la base de datos al almacenamiento externo.
     */
    fun exportDatabase(context: Context) {
        try {
            val dbPath = context.getDatabasePath("app_database").absolutePath
            val exportPath = File(context.getExternalFilesDir(null), "exported_database.db")

            File(dbPath).copyTo(exportPath, overwrite = true)

            Log.d("Database", "Base de datos exportada a: ${exportPath.absolutePath}")
        } catch (e: Exception) {
            Log.e("Database", "Error al exportar la base de datos: ${e.message}")
        }
    }
}
