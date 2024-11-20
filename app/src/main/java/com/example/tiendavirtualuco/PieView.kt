package com.example.tiendavirtualuco

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageButton
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.tiendavirtualuco.favoritos.MisFavoritosActivity
import com.example.tiendavirtualuco.pie.service.command.CommandsViewsEnum
import com.example.tiendavirtualuco.pie.service.command.settings.CommandFactory
import com.example.tiendavirtualuco.pie.service.command.settings.CommandManager

class PieView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var imgCompras: ImageView
    private var imgOfertas: ImageView
    private var imgProductos: ImageView
    private var imgHistoricoProductos: ImageView
    private var imgFavoritos: ImageView

    init {
        inflate(context, R.layout.pie_layout,this)
        imgCompras = findViewById<ImageButton>(R.id.icCompra)
        imgOfertas = findViewById<ImageButton>(R.id.icBuscarOfertas)
        imgProductos = findViewById<ImageButton>(R.id.icMisProductos)
        imgHistoricoProductos = findViewById<ImageButton>(R.id.icHistorialCompra)
        imgFavoritos = findViewById<ImageButton>(R.id.icFavoritos)
        initIcons()
    }

    private fun initIcons() {
        val iconCommandMap = mapOf(
            R.id.icFavoritos to Pair(CommandsViewsEnum.OPEN_FAVORITES, MisFavoritosActivity::class.java)
            // TODO: Add more icons and their corresponding commands here
        )

        for ((iconId, commnandInfo) in iconCommandMap) {
            val (commandType, destination) = commnandInfo
            val icon = findViewById<android.widget.ImageView>(iconId)
            val command = CommandFactory.createCommand(commandType, context, destination)
            CommandManager.registerCommand(command, commandType)
            icon.setOnClickListener {
                CommandManager.executeCommand(commandType)
            }
        }
    }
}