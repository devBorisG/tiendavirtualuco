package com.example.tiendavirtualuco

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout

class CabeceraView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var imgBuscar: ImageView
    private var etBuscar: EditText

    init {
        inflate(context, R.layout.cabecera_layout,this)
        imgBuscar = findViewById(R.id.icBuscar)
        etBuscar = findViewById(R.id.etBuscar)
    }

}