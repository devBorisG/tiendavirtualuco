package com.example.tiendavirtualuco

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    // Datos quemados para el login
    private val validEmail = "usuario@ejemplo.com"
    private val validPassword = "123456"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login)

        // Ajustar el padding según los insets del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtener las referencias a los campos del layout
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)

        // Manejar el botón de iniciar sesión
        val iniciarSesionButton: TextView = findViewById(R.id.btn_iniciar_sesion)
        iniciarSesionButton.setOnClickListener {
            // Validar los campos antes de proceder con el login
            if (validateFields()) {
                // Probar con datos quemados
                if (emailEditText.text.toString() == validEmail && passwordEditText.text.toString() == validPassword) {
                    // Si el login es exitoso, muestra un mensaje de sesión iniciada
                    Toast.makeText(this, "Sesión iniciada con éxito", Toast.LENGTH_SHORT).show()
                } else {
                    // Mostrar un mensaje de error si las credenciales son incorrectas
                    Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Manejar el texto de registro
        val registerText: TextView = findViewById(R.id.registerText)
        registerText.setOnClickListener {
            // Navega a RegisterActivity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)


        }
    }

    // Método para validar los campos del formulario de login
    private fun validateFields(): Boolean {
        var isValid = true

        // Validar el correo electrónico
        val email = emailEditText.text.toString()
        if (email.isEmpty()) {
            emailEditText.error = "El correo es obligatorio"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.error = "Correo no válido"
            isValid = false
        }

        // Validar la contraseña
        if (passwordEditText.text.toString().isEmpty()) {
            passwordEditText.error = "La contraseña es obligatoria"
            isValid = false
        }

        return isValid
    }
}
