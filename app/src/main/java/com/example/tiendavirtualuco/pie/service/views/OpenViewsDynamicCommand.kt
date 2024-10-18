package com.example.tiendavirtualuco.pie.service.views

import android.content.Context
import android.content.Intent
import com.example.tiendavirtualuco.pie.service.command.Command

class OpenViewsDynamicCommand(private val context: Context, private val destination: Class<*>):
    Command {
    override fun execute() {
        val intent = Intent(context, destination)
        context.startActivity(intent)
    }
}