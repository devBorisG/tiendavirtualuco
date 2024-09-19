package com.example.tiendavirtualuco

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {

    private lateinit var nombreEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var celularEditText: EditText
    private lateinit var tipoUsuarioEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtener referencias a los campos del layout usando los IDs correctos
        nombreEditText = findViewById(R.id.rg_nombre)  // Campo Nombre
        emailEditText = findViewById(R.id.rg_emailEditText)  // Campo Email
        passwordEditText = findViewById(R.id.rg_password)  // Campo Contraseña
        celularEditText = findViewById(R.id.rg_celular)  // Campo Celular
        tipoUsuarioEditText = findViewById(R.id.rg_tipo_usuario)  // Campo Tipo de Usuario

        val crearCuentaButton: TextView = findViewById(R.id.btn_crear_cuenta)
        crearCuentaButton.setOnClickListener {
            // Validar campos antes de continuar
            if (validateFields()) {
                // Si todos los campos son válidos, navega a MainActivity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        val registerText: TextView = findViewById(R.id.btn_bk_inicio_sesion)
        registerText.setOnClickListener {
            // Navega a RegisterActivity
            val intent = Intent(    this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    // Método para validar los campos
    private fun validateFields(): Boolean {
        var isValid = true

        // Validar nombre
        if (nombreEditText.text.toString().isEmpty()) {
            nombreEditText.error = "El nombre es obligatorio"
            isValid = false
        }

        // Validar email
        val email = emailEditText.text.toString()
        if (email.isEmpty()) {
            emailEditText.error = "El correo es obligatorio"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.error = "Correo no válido"
            isValid = false
        }

        // Validar contraseña
        if (passwordEditText.text.toString().isEmpty()) {
            passwordEditText.error = "La contraseña es obligatoria"
            isValid = false
        } else if (passwordEditText.text.toString().length < 6) {
            passwordEditText.error = "La contraseña debe tener al menos 6 caracteres"
            isValid = false
        }

        // Validar celular
        val celular = celularEditText.text.toString()
        if (celular.isEmpty()) {
            celularEditText.error = "El número de celular es obligatorio"
            isValid = false
        } else if (celular.length != 10) {
            celularEditText.error = "El número de celular debe tener 10 dígitos"
            isValid = false
        }

        // Validar tipo de usuario
        if (tipoUsuarioEditText.text.toString().isEmpty()) {
            tipoUsuarioEditText.error = "El tipo de usuario es obligatorio"
            isValid = false
        }

        // Mostrar mensaje si hay errores
        if (!isValid) {
            Toast.makeText(this, "Por favor, completa todos los campos correctamente", Toast.LENGTH_SHORT).show()
        }

        return isValid
    }
}
