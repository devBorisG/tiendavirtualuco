package com.example.tiendavirtualuco.homepageproductos.service.command.settings

import android.content.Context
import com.example.tiendavirtualuco.homepageproductos.service.command.Command
import com.example.tiendavirtualuco.homepageproductos.service.command.CommandsViewsEnum
import com.example.tiendavirtualuco.homepageproductos.service.views.OpenFavoritesCommand

object CommandFactory {
    fun createCommand(type: CommandsViewsEnum, context: Context): Command {
        return when (type) {
            CommandsViewsEnum.OPEN_FAVORITES -> OpenFavoritesCommand(context)
            //TODO: Add more commands

            else -> throw IllegalArgumentException("Comando desconocido")
        }
    }
}