package com.example.tiendavirtualuco

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotificacionesAdapter(private val notificacionesList: List<String>) : RecyclerView.Adapter<NotificacionesAdapter.ViewHolder>() {



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tv_notificacion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notificacion, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notificacion = notificacionesList[position]
        holder.textView.text = notificacion

        // Añadir el listener para manejar el clic en la notificación
        holder.itemView.setOnClickListener {
            // Crear un Intent para abrir DetalleNotificacionActivity
            val intent = Intent(holder.itemView.context, DetalleNotificacionActivity::class.java)
            intent.putExtra("detalleNotificacion", notificacion)
            holder.itemView.context.startActivity(intent)

        }
    }

    override fun getItemCount() = notificacionesList.size
}
