package com.example.tiendavirtualuco.mis_productos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.mis_productos.adapter.AdaptadorProducto
import com.example.tiendavirtualuco.mis_productos.service.command.CommandsViewsEnum
import com.example.tiendavirtualuco.mis_productos.service.command.settings.CommandFactory
import com.example.tiendavirtualuco.mis_productos.service.command.settings.CommandManager
import com.example.tiendavirtualuco.mis_productos.service.observe.implementation.LoggingCommandObserver

class MisProductosActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mis_productos)
        CommandManager.addObserver(LoggingCommandObserver())
        initRecyclerView()
        initIcons()
        configBotonAñadirProducto()
        configBotonEditar()
    }

    private fun initRecyclerView(){
        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler_productos)
        recyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(this, 2)
        recyclerView.adapter = AdaptadorProducto(ProveedorProducto.listaProductos)
    }

    fun configBotonAñadirProducto(){
        val btnBack = findViewById<Button>(R.id.BtAñadirProductoMisProductos)
        btnBack.setOnClickListener {
            val intent = Intent(this, AñadirProductoActivity ::class.java)
            startActivity(intent)
        }
    }
    fun configBotonEditar(){
        val btnBack = findViewById<ImageView>(R.id.iconoEditarMisProductos)
        btnBack.setOnClickListener {
            val intent = Intent(this, EditarProductoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initIcons() {
        val iconCommandMapFavorties = mapOf(
            R.id.ic_favorites to CommandsViewsEnum.OPEN_FAVORITES

            // TODO: Add more icons and their corresponding commands here
        )

        val iconCommandMapMyProducts = mapOf(
            R.id.ic_my_products to CommandsViewsEnum.OPEN_MY_PRODUCTS

            // TODO: Add more icons and their corresponding commands here
        )




        for ((iconId, commandType) in iconCommandMapFavorties) {
            val icon = findViewById<android.widget.ImageView>(iconId)
            val command = CommandFactory.createCommandFavorites(commandType, this)
            CommandManager.registerCommand(command, commandType)
            icon.setOnClickListener {
                CommandManager.executeCommand(commandType)
            }
        }

        for ((iconId, commandType) in iconCommandMapMyProducts) {
            val icon = findViewById<android.widget.ImageView>(iconId)
            val command = CommandFactory.createCommandMyProducts(commandType, this)
            CommandManager.registerCommand(command, commandType)
            icon.setOnClickListener {
                CommandManager.executeCommand(commandType)
            }
        }




    }


}