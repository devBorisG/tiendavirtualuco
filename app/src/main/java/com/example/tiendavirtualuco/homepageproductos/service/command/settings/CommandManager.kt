package com.example.tiendavirtualuco.homepageproductos.service.command.settings

import com.example.tiendavirtualuco.homepageproductos.service.command.Command
import com.example.tiendavirtualuco.homepageproductos.service.observe.CommandObserver
import com.example.tiendavirtualuco.homepageproductos.service.command.CommandsViewsEnum

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