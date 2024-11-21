package com.example.tiendavirtualuco.reportes

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.tiendavirtualuco.R
import com.example.tiendavirtualuco.reportes.data.local.DataBase
import com.example.tiendavirtualuco.reportes.data.local.database.AppDatabase
import com.example.tiendavirtualuco.reportes.entity.TipoReporteEntity
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MisReportes : AppCompatActivity() {
    private var Elijaunaopcion: Spinner? = null
    private var detalleReporte: EditText? = null
    private var enviarButton: Button? = null
    private lateinit var appDatabase: AppDatabase // Base de datos local
    private lateinit var firestore: FirebaseFirestore // Base de datos Firestore
    private lateinit var auth: FirebaseAuth // Autenticación Firebase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reporte)

        // Inicializar Firebase
        FirebaseApp.initializeApp(this)
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        // Inicializar la base de datos Room
        appDatabase = DataBase.getDatabase(applicationContext)

        // Insertar datos de ejemplo en la base de datos local
        insertarDatosDeEjemplo()

        // Inicializar componentes
        Elijaunaopcion = findViewById(R.id.Elijaunaopcion)
        detalleReporte = findViewById(R.id.editTextText)
        enviarButton = findViewById(R.id.button2)

        // Llenar el Spinner con las opciones de la base de datos
        cargarOpciones()

        // Deshabilitar el botón de enviar al principio
        enviarButton?.isEnabled = false

        // Configurar listeners para verificar campos
        configurarListeners()

        // Configurar botón de enviar
        enviarButton?.setOnClickListener {
            enviarReporte()
        }
    }

    private fun configurarListeners() {
        Elijaunaopcion?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                verificarCampos() // Verificar los campos cada vez que se selecciona algo
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        detalleReporte?.addTextChangedListener(object : android.text.TextWatcher {
            override fun afterTextChanged(s: android.text.Editable?) {
                verificarCampos() // Verificar los campos cuando cambie el texto
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun insertarDatosDeEjemplo() {
        lifecycleScope.launch(Dispatchers.IO) {
            val opcionesExistentes = appDatabase.tipoReporteDao().getAllOptions()
            if (opcionesExistentes.isEmpty()) {
                val opciones = listOf(
                    TipoReporteEntity(descripcion = "No me gustó el producto"),
                    TipoReporteEntity(descripcion = "Muy Pequeño"),
                    TipoReporteEntity(descripcion = "No me llegó el producto solicitado"),
                    TipoReporteEntity(descripcion = "No era lo que esperaba, llegó con defecto"),
                    TipoReporteEntity(descripcion = "Hice pago de producto y no llegó"),
                    TipoReporteEntity(descripcion = "El producto no llegó a su destino"),
                    TipoReporteEntity(descripcion = "Presentó diferencia con el material")
                )
                appDatabase.tipoReporteDao().insertOption(opciones)
                Log.d("MisReportes", "Datos de ejemplo insertados en la base de datos.")
            } else {
                Log.d("MisReportes", "Los datos de ejemplo ya existen en la base de datos.")
            }
        }
    }

    private fun cargarOpciones() {
        lifecycleScope.launch(Dispatchers.IO) {
            val opciones = appDatabase.tipoReporteDao().getAllOptions()
            runOnUiThread {
                if (opciones.isNotEmpty()) {
                    val listaOpciones = opciones.map { it.descripcion }
                    val adaptador: ArrayAdapter<String> = ArrayAdapter(
                        this@MisReportes,
                        android.R.layout.simple_spinner_item,
                        listaOpciones
                    )
                    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    Elijaunaopcion?.adapter = adaptador
                } else {
                    Toast.makeText(this@MisReportes, "No hay opciones disponibles", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun verificarCampos() {
        val opcionSeleccionada = Elijaunaopcion?.selectedItem?.toString()
        val detalle = detalleReporte?.text.toString()
        enviarButton?.isEnabled = !opcionSeleccionada.isNullOrEmpty() && detalle.isNotEmpty()
    }

    private fun enviarReporte() {
        val opcionSeleccionada = Elijaunaopcion?.selectedItem?.toString()
        val detalle = detalleReporte?.text.toString()

        if (opcionSeleccionada.isNullOrEmpty() || detalle.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos.", Toast.LENGTH_SHORT).show()
            return
        }

        val reporte = hashMapOf(
            "tipoReporte" to opcionSeleccionada,
            "detalle" to detalle,
            "usuario" to (auth.currentUser?.email ?: "Anónimo"),
            "timestamp" to System.currentTimeMillis()
        )

        firestore.collection("reportes")
            .add(reporte)
            .addOnSuccessListener {
                Toast.makeText(this, "Reporte enviado con éxito", Toast.LENGTH_SHORT).show()
                detalleReporte?.text?.clear()
                Elijaunaopcion?.setSelection(0)
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al enviar el reporte: ${e.message}", Toast.LENGTH_SHORT).show()
                Log.e("Firebase", "Error al enviar reporte", e)
            }
    }
}
