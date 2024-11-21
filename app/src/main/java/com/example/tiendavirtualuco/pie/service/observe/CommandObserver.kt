package com.example.tiendavirtualuco.pie.service.observe

interface CommandObserver {
    fun onCommandExecuted(commandName: String)
}