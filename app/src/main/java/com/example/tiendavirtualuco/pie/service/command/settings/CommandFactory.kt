package com.example.tiendavirtualuco.pie.service.command.settings

import android.content.Context
import com.example.tiendavirtualuco.pie.service.command.Command
import com.example.tiendavirtualuco.pie.service.command.CommandsViewsEnum
import com.example.tiendavirtualuco.pie.service.views.OpenViewsDynamicCommand

object CommandFactory {
    fun createCommand(type: CommandsViewsEnum, context: Context, destination: Class<*>): Command {
        return when (type) {
            CommandsViewsEnum.OPEN_FAVORITES -> OpenViewsDynamicCommand(context, destination)
            //TODO: Add more commands

            else -> throw IllegalArgumentException("Comando desconocido")
        }
    }
}