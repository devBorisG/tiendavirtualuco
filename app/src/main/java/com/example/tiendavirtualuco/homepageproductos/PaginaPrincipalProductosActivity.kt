package com.example.tiendavirtualuco.homepageproductos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.favoritos.MisFavoritosActivity
import com.example.tiendavirtualuco.homepageproductos.adapter.AdaptadorProducto
import com.example.tiendavirtualuco.homepageproductos.service.command.CommandsViewsEnum
import com.example.tiendavirtualuco.homepageproductos.service.command.settings.CommandFactory
import com.example.tiendavirtualuco.homepageproductos.service.command.settings.CommandManager
import com.example.tiendavirtualuco.homepageproductos.service.observe.implementation.LoggingCommandObserver

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
            R.id.icFavoritos to Pair(CommandsViewsEnum.OPEN_FAVORITES, MisFavoritosActivity::class.java)
            // TODO: Add more icons and their corresponding commands here
        )

        for ((iconId, commnandInfo) in iconCommandMap) {
            val (commandType, destination) = commnandInfo
            val icon = findViewById<android.widget.ImageView>(iconId)
            val command = CommandFactory.createCommand(commandType, this, destination)
            CommandManager.registerCommand(command, commandType)
            icon.setOnClickListener {
                CommandManager.executeCommand(commandType)
            }
        }
    }
}