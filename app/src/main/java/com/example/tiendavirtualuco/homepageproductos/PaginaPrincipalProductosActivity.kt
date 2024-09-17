package com.example.tiendavirtualuco.homepageproductos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.favoritos.MisFavoritosActivity
import com.example.tiendavirtualuco.homepageproductos.adapter.AdaptadorProducto
import com.example.tiendavirtualuco.pie.service.command.CommandsViewsEnum
import com.example.tiendavirtualuco.pie.service.command.settings.CommandFactory
import com.example.tiendavirtualuco.pie.service.command.settings.CommandManager
import com.example.tiendavirtualuco.pie.service.observe.implementation.LoggingCommandObserver

class PaginaPrincipalProductosActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagina_principal_productos)
        CommandManager.addObserver(LoggingCommandObserver())
        initRecyclerView()
    }

    private fun initRecyclerView(){
        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler_productos)
        recyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(this, 2)
        recyclerView.adapter = AdaptadorProducto(ProveedorProducto.listaProductos)
    }


}