package com.example.tiendavirtualuco

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern

class ReferFriendActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refer_friend)

        val emailEditText = findViewById<EditText>(R.id.et_friend_email)
        val sendInvitationButton = findViewById<Button>(R.id.btn_send_invitation)
        val backButton = findViewById<ImageButton>(R.id.btn_back)

        sendInvitationButton.setOnClickListener {
            val email = emailEditText.text.toString()

            // Verificar si el campo está vacío
            if (email.isNotEmpty()) {
                // Verificar si el correo electrónico es válido
                if (isValidEmail(email)) {
                    Toast.makeText(this, "Invitación enviada a $email", Toast.LENGTH_SHORT).show()

                    // Volver a la actividad anterior
                    finish()
                } else {
                    Toast.makeText(this, "Por favor introduce una dirección de correo electrónico válida", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor ingrese una dirección de correo electrónico", Toast.LENGTH_SHORT).show()
            }
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun isValidEmail(email: String): Boolean {
        // Expresión regular para validar correos electrónicos
        val emailPattern = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
            Pattern.CASE_INSENSITIVE
        )
        return emailPattern.matcher(email).matches()
    }
}
