package com.example.tiendavirtualuco.mis_productos.service.command.settings

import com.example.tiendavirtualuco.mis_productos.service.command.Command
import com.example.tiendavirtualuco.mis_productos.service.observe.CommandObserver
import com.example.tiendavirtualuco.mis_productos.service.command.CommandsViewsEnum

object CommandManager {
    private val commands = mutableMapOf<CommandsViewsEnum, Command>()
    private val observers = mutableListOf<CommandObserver>()

    fun registerCommand(command: Command, commandType: CommandsViewsEnum) {
        commands[commandType] = command
    }

    fun executeCommand(commandType: CommandsViewsEnum) {
        commands[commandType]?.execute()
        notifyObservers(commandType)
    }

    fun addObserver(observer: CommandObserver) {
        observers.add(observer)
    }

    private fun notifyObservers(commandName: CommandsViewsEnum) {
        observers.forEach { it.onCommandExecuted(commandName.name) }
    }
}