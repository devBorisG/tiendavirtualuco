package com.example.tiendavirtualuco.pie.service.observe.implementation

import android.util.Log
import com.example.tiendavirtualuco.pie.service.command.CommandsViewsEnum
import com.example.tiendavirtualuco.pie.service.observe.CommandObserver

class LoggingCommandObserver : CommandObserver {
    override fun onCommandExecuted(commandName: String) {
        Log.d("CommandObserver", "Command executed: $commandName")
    }
}