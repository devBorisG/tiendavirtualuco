package com.example.tiendavirtualuco.homepageproductos.service.views

import android.content.Context
import android.content.Intent
import com.example.tiendavirtualuco.favoritos.MisFavoritosActivity
import com.example.tiendavirtualuco.homepageproductos.service.command.Command

class OpenFavoritesCommand(private val context: Context): Command {
    override fun execute() {
        val intent = Intent(context, MisFavoritosActivity::class.java)
        context.startActivity(intent)
    }
}