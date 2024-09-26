package com.example.tiendavirtualuco.tienda

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendavirtualuco.R


class CrearTiendaActivity : AppCompatActivity() {
    private lateinit var storeNameInput: EditText
    private lateinit var storeWebAddressInput: EditText
    private lateinit var storeDescriptionInput: EditText
    private lateinit var storeTypeInput: EditText
    private lateinit var addressInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_tienda)
        // Referencias a los EditText
        storeNameInput = findViewById(R.id.storeNameInput)
        storeWebAddressInput = findViewById(R.id.storeWebAddressInput)
        storeDescriptionInput = findViewById(R.id.storeDescriptionInput)
        storeTypeInput = findViewById(R.id.storeTypeInput)
        addressInput = findViewById(R.id.addressInput)

        // Configurar el botón de crear tienda con la validación
        botonCrearTiendaFormulario()
    }

    private fun botonCrearTiendaFormulario() {
        val btnCreate = findViewById<Button>(R.id.createButton)
        btnCreate.setOnClickListener {
            // Llamar a la función de validación
            if (validarFormulario()) {
                // Si la validación es exitosa, proceder a la siguiente actividad
                val intent = Intent(this, TiendaAgregarProductoActivity::class.java)
                startActivity(intent)
            }
        }
    }

    // Función para validar el formulario
    private fun validarFormulario(): Boolean {
        // Validar que todos los campos no estén vacíos
        if (storeNameInput.text.toString().isEmpty()) {
            storeNameInput.error = "Store name is required"
            storeNameInput.requestFocus()
            return false
        }

        if (storeWebAddressInput.text.toString().isEmpty()) {
            storeWebAddressInput.error = "Web address is required"
            storeWebAddressInput.requestFocus()
            return false
        } else if (!android.util.Patterns.WEB_URL.matcher(storeWebAddressInput.text.toString())
                .matches()
        ) {
            storeWebAddressInput.error = "Invalid web address format"
            storeWebAddressInput.requestFocus()
            return false
        }

        if (storeDescriptionInput.text.toString().isEmpty()) {
            storeDescriptionInput.error = "Store description is required"
            storeDescriptionInput.requestFocus()
            return false
        }

        if (storeTypeInput.text.toString().isEmpty()) {
            storeTypeInput.error = "Store type is required"
            storeTypeInput.requestFocus()
            return false
        }

        if (addressInput.text.toString().isEmpty()) {
            addressInput.error = "Address is required"
            addressInput.requestFocus()
            return false
        }

        // Si todas las validaciones son exitosas
        Toast.makeText(this, "Store details are valid", Toast.LENGTH_SHORT).show()
        return true
    }
}