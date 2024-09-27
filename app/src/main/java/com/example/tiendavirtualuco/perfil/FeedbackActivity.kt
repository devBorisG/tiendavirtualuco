package com.example.tiendavirtualuco.perfil

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tiendavirtualuco.R

class FeedbackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        // Referencias a los elementos del layout
        val editTextFeedback = findViewById<EditText>(R.id.et_feedback)
        val btnSendFeedback = findViewById<Button>(R.id.btn_send_feedback)
        val btnBack = findViewById<ImageButton>(R.id.btn_back)


        btnSendFeedback.setOnClickListener {
            val feedback = editTextFeedback.text.toString()

            // Verifica que el campo no esté vacío
            if (feedback.isNotEmpty()) {

                Toast.makeText(this, "Feedback enviado", Toast.LENGTH_SHORT).show()

                // Regresar a la actividad anterior (MainActivity)
                finish()
            } else {
                Toast.makeText(this, "Por favor escribe un comentario", Toast.LENGTH_SHORT).show()
            }
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}
