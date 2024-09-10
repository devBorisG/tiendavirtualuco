package com.example.tiendavirtualuco.mis_productos.service.observe

interface CommandObserver {
    fun onCommandExecuted(commandName: String)
}