package com.example.tiendavirtualuco.homepageproductos.service.observe

import com.example.tiendavirtualuco.homepageproductos.service.command.CommandsViewsEnum

interface CommandObserver {
    fun onCommandExecuted(commandName: String)
}