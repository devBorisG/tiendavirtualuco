package com.example.tiendavirtualuco.reportes

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import com.example.tiendavirtualuco.R
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MisReportes : AppCompatActivity() {
    private var Elijaunaopcion: Spinner? = null
    private var detalleReporte: EditText? = null
    private var enviarButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reporte)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val reports = listOf(
            Report("230450230207483", "Completada", "15 de agosto de 2024 - 10:00", "Método de reporte: Entrega a domicilio", "220197701", "Apple iPhone 14 Pro 256 GB", "$5.000.000"),
            Report("230450230207484", "Pendiente", "1 de septiembre de 2024 - 12:45", "Método de reporte: Retiro en tienda", "220197702", "Samsung Galaxy S22 Ultra 128 GB", "$4.200.000"),
            Report("230450230207482", "Anulada", "30 de julio de 2024 - 15:32", "Método de reporte: Entrega de producto en sucursal de Servientrega", "220197700", "Dual SIM Redmi Note 13 Pro+ 5G Xiaomi 8/256 GB", "$1.503.900")
        )
        recyclerView.adapter = ReporteAdapter(reports)
        // Inicializar componentes
        Elijaunaopcion = findViewById(R.id.Elijaunaopcion)
        detalleReporte = findViewById(R.id.editTextText)
        enviarButton = findViewById(R.id.button2)

        // Opciones del Spinner
        val listaOpciones = arrayOf(
            "Elija una opcion", "No me gustó el producto", "Muy Pequeño",
            "No me llego el producto solicitado", "No era lo que esperaba llego con defecto",
            "Hice pago de producto y no llegó", "El producto no llego a su destino",
            "Presentó diferencia con el material"
        )
        val adaptador: ArrayAdapter<String> = ArrayAdapter(this, R.layout.spinner_reportes, listaOpciones)
        Elijaunaopcion?.adapter = adaptador

        // Deshabilitar el botón de enviar al principio
        enviarButton?.isEnabled = false

        // Escuchar cambios en los campos
        Elijaunaopcion?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                verificarCampos()  // Verificar los campos cada vez que se selecciona algo
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        detalleReporte?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                verificarCampos()  // Verificar los campos cuando cambie el texto
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun verificarCampos() {
        val opcionSeleccionada = Elijaunaopcion?.selectedItem.toString()
        val detalle = detalleReporte?.text.toString()

        // Activar el botón solo si se selecciona una opción válida y se escribe un detalle
        enviarButton?.isEnabled = opcionSeleccionada != "Elija una opcion" && detalle.isNotEmpty()
    }

    fun enviarReporte(view: View) {
        Toast.makeText(this, "El Reporte fue exitoso", Toast.LENGTH_SHORT).show()
    }
}