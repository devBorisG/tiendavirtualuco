package com.example.tiendavirtualuco.mis_productos.service.views

import android.content.Context
import android.content.Intent
import com.example.tiendavirtualuco.favoritos.MisFavoritosActivity
import com.example.tiendavirtualuco.mis_productos.service.command.Command

class OpenFavoritesCommand(private val context: Context): Command {
    override fun execute() {
        val intent = Intent(context, MisFavoritosActivity::class.java)
        context.startActivity(intent)
    }
}