package com.example.tiendavirtualuco.mis_productos.service.views

import android.content.Context
import android.content.Intent
import com.example.tiendavirtualuco.MainActivity
import com.example.tiendavirtualuco.mis_productos.service.command.Command

class OpenMyproductsCommand(private val context: Context): Command {
    override fun execute() {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }
}