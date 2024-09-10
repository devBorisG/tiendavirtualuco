package com.example.tiendavirtualuco.mis_productos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.mis_productos.adapter.AdaptadorProducto
import com.example.tiendavirtualuco.mis_productos.service.command.CommandsViewsEnum
import com.example.tiendavirtualuco.mis_productos.service.command.settings.CommandFactory
import com.example.tiendavirtualuco.mis_productos.service.command.settings.CommandManager
import com.example.tiendavirtualuco.mis_productos.service.observe.implementation.LoggingCommandObserver

class PaginaPrincipalProductosActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagina_principal_productos)
        CommandManager.addObserver(LoggingCommandObserver())
        initRecyclerView()
        initIcons()
    }

    private fun initRecyclerView(){
        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler_productos)
        recyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(this, 2)
        recyclerView.adapter = AdaptadorProducto(ProveedorProducto.listaProductos)
    }

    private fun initIcons() {
        val iconCommandMap = mapOf(
            R.id.ic_favorites to CommandsViewsEnum.OPEN_FAVORITES
            // TODO: Add more icons and their corresponding commands here
        )

        for ((iconId, commandType) in iconCommandMap) {
            val icon = findViewById<android.widget.ImageView>(iconId)
            val command = CommandFactory.createCommand(commandType, this)
            CommandManager.registerCommand(command, commandType)
            icon.setOnClickListener {
                CommandManager.executeCommand(commandType)
            }
        }
    }
}