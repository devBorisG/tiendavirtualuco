package com.example.tiendavirtualuco.trazabilidad.fragmentos

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.trazabilidad.adaptador.AdaptadorProductos
import com.example.tiendavirtualuco.trazabilidad.Producto // Importamos tu clase Producto

class ListaProductos : Fragment(R.layout.trazabilidad_pagina_principal_productos_recyvleview) {

    private lateinit var productAdapter: AdaptadorProductos

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Referencia al RecyclerView del layout del fragmento
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewProducts)

        // Inicia el adaptador con la lista de productos
        productAdapter = AdaptadorProductos(getProductList())

        // Configuración del RecyclerView
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productAdapter
        }
    }


    private fun getProductList(): List<Producto> {

        return listOf(
            Producto("Producto 1", 25000.0, "Descripción del producto 1"),
            Producto("Producto 2", 40000.0, "Descripción del producto 2"),
            Producto("Producto 3", 15000.0, "Descripción del producto 3")
        )
    }
}