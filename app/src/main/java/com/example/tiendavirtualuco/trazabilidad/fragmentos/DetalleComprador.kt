package com.example.tiendavirtualuco.trazabilidad.fragmentos



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.tiendavirtualuco.R

class DetalleComprador : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout para este fragmento
        return inflater.inflate(R.layout.trazabilidad_detalle_comprador, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val btnOrderTracking = view.findViewById<Button>(R.id.btnOrderTracking)
        btnOrderTracking.setOnClickListener {
            // Acciones cuando se hace clic en el botón
        }

        // Puedes agregar más lógica aquí para interactuar con otras vistas
    }
}