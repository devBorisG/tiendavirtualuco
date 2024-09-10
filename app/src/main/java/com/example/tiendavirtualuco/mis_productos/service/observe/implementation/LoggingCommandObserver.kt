package com.example.tiendavirtualuco.mis_productos.service.observe.implementation

import android.util.Log
import com.example.tiendavirtualuco.mis_productos.service.observe.CommandObserver

class LoggingCommandObserver : CommandObserver {
    override fun onCommandExecuted(commandName: String) {
        Log.d("CommandObserver", "Command executed: $commandName")
    }
}