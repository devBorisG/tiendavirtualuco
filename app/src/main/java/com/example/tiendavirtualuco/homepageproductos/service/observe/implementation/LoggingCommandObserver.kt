package com.example.tiendavirtualuco.homepageproductos.service.observe.implementation

import android.util.Log
import com.example.tiendavirtualuco.homepageproductos.service.command.CommandsViewsEnum
import com.example.tiendavirtualuco.homepageproductos.service.observe.CommandObserver

class LoggingCommandObserver : CommandObserver {
    override fun onCommandExecuted(commandName: String) {
        Log.d("CommandObserver", "Command executed: $commandName")
    }
}