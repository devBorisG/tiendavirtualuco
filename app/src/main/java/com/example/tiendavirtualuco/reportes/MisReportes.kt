package com.example.tiendavirtualuco.reportes

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import com.example.tiendavirtualuco.R
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.lifecycleScope
import com.example.tiendavirtualuco.reportes.data.local.DataBase
import com.example.tiendavirtualuco.reportes.data.local.database.AppDatabase
import com.example.tiendavirtualuco.reportes.entity.TipoReporteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MisReportes : AppCompatActivity() {
    private var Elijaunaopcion: Spinner? = null
    private var detalleReporte: EditText? = null
    private var enviarButton: Button? = null
    private lateinit var appDatabase: AppDatabase  // Base de datos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reporte)

        // Inicializar la base de datos Room utilizando DataBase.getDatabase
        appDatabase = DataBase.getDatabase(applicationContext) // Llamada correcta

        // Inicializar componentes
        Elijaunaopcion = findViewById(R.id.Elijaunaopcion)
        detalleReporte = findViewById(R.id.editTextText)
        enviarButton = findViewById(R.id.button2)

        // Llenar el Spinner con las opciones de la base de datos
        cargarOpciones()

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

    // Método para insertar datos de ejemplo en la base de datos
    private fun insertarDatosDeEjemplo() {
        lifecycleScope.launch(Dispatchers.IO) {
            // Verificar si ya existen datos en la tabla para no insertarlos nuevamente
            val opcionesExistentes = appDatabase.tipoReporteDao().getAllOptions()
            if (opcionesExistentes.isEmpty()) {
                val opciones = listOf(
                    TipoReporteEntity(descripcion = "No me gustó el producto"),
                    TipoReporteEntity(descripcion = "Muy Pequeño"),
                    TipoReporteEntity(descripcion = "No me llego el producto solicitado"),
                    TipoReporteEntity(descripcion = "No era lo que esperaba llego con defecto"),
                    TipoReporteEntity(descripcion = "Hice pago de producto y no llegó"),
                    TipoReporteEntity(descripcion = "El producto no llego a su destino"),
                    TipoReporteEntity(descripcion = "Presentó diferencia con el material")
                )
                // Insertar las opciones de tipo de reporte en la base de datos
                appDatabase.tipoReporteDao().insertOption(opciones)
            }
        }
    }

    // Método para cargar las opciones del Spinner desde la base de datos
    private fun cargarOpciones() {
        lifecycleScope.launch(Dispatchers.IO) {
            val opciones = appDatabase.tipoReporteDao().getAllOptions()

            // Ejecutamos en el hilo principal para actualizar la UI
            runOnUiThread {
                val listaOpciones = opciones.map { it.descripcion } // Extraemos la descripción de las opciones
                val adaptador: ArrayAdapter<String> = ArrayAdapter(this@MisReportes, R.layout.spinner_reportes, listaOpciones)
                Elijaunaopcion?.adapter = adaptador
            }
        }
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
