package com.example.tiendavirtualuco.pie.service.observe

import com.example.tiendavirtualuco.pie.service.command.CommandsViewsEnum

interface CommandObserver {
    fun onCommandExecuted(commandName: String)
}