package com.example.tiendavirtualuco

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditProfileActivity : AppCompatActivity() {

    private val MAX_LENGTH_NAME = 15
    private val MAX_LENGTH_PHONE = 10
    private val MIN_LENGTH_PASSWORD = 6
    private val MAX_LENGTH_PASSWORD = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        // Referencia al botón de guardar
        val btnSave = findViewById<Button>(R.id.btn_save)

        // Acción cuando se presiona el botón de guardar
        btnSave.setOnClickListener {
            // Obtener los valores de los campos
            val firstName = findViewById<EditText>(R.id.et_first_name).text.toString()
            val lastName = findViewById<EditText>(R.id.et_last_name).text.toString()
            val phone = findViewById<EditText>(R.id.et_phone).text.toString()
            val password = findViewById<EditText>(R.id.et_password).text.toString()

            // Validar los campos
            if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                showToast("Por favor, complete todos los campos.")
                return@setOnClickListener
            }

            if (firstName.length > MAX_LENGTH_NAME || lastName.length > MAX_LENGTH_NAME) {
                showToast("El nombre y el apellido deben tener un máximo de $MAX_LENGTH_NAME caracteres.")
                return@setOnClickListener
            }

            if (phone.length != MAX_LENGTH_PHONE) {
                showToast("El teléfono debe tener exactamente $MAX_LENGTH_PHONE números.")
                return@setOnClickListener
            }

            if (password.length < MIN_LENGTH_PASSWORD || password.length > MAX_LENGTH_PASSWORD) {
                showToast("La contraseña debe tener entre $MIN_LENGTH_PASSWORD y $MAX_LENGTH_PASSWORD caracteres.")
                return@setOnClickListener
            }


            val currentFirstName = "Pepe"
            val currentLastName = "Arias"
            val currentPhone = "1234567890"

            if (firstName == currentFirstName && lastName == currentLastName && phone == currentPhone) {
                showToast("No ha realizado cambios en los campos.")
                return@setOnClickListener
            }

            // Crear un Intent para enviar datos de vuelta a MainActivity
            val resultIntent = Intent()
            resultIntent.putExtra("EXTRA_FIRST_NAME", firstName)
            resultIntent.putExtra("EXTRA_LAST_NAME", lastName)
            resultIntent.putExtra("EXTRA_PHONE", phone)
            setResult(RESULT_OK, resultIntent)

            // Mostrar un mensaje de "Información actualizada" usando Toast
            showToast("Información actualizada")

            finish()
        }

        val btnBack = findViewById<ImageButton>(R.id.btn_back)

        // Acción cuando se presiona el botón de retroceso
        btnBack.setOnClickListener {
            finish()
        }

        val etPhone = findViewById<EditText>(R.id.et_phone)
        etPhone.inputType = android.text.InputType.TYPE_CLASS_PHONE
    }

    // Función para mostrar mensajes de Toast
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
