package com.example.tiendavirtualuco

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NotificacionesSwitch : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notificaciones_switch)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtener referencias a los switches
        val switchSMS: Switch = this.findViewById(R.id.switch_sms)
        val switchWhatsApp: Switch = this.findViewById(R.id.switch_whatsapp)
        val switchEmail: Switch = this.findViewById(R.id.switch_email)
        val switchInApp: Switch = this.findViewById(R.id.switch_inapp)

        // Configurar listeners para cada switch
        switchSMS.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Acciones cuando el switch de SMS está activado
            } else {
                // Acciones cuando el switch de SMS está desactivado
            }
        }

        switchWhatsApp.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Acciones cuando el switch de WhatsApp está activado
            } else {
                // Acciones cuando el switch de WhatsApp está desactivado
            }
        }

        switchEmail.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Acciones cuando el switch de Correo está activado
            } else {
                // Acciones cuando el switch de Correo está desactivado
            }
        }

        switchInApp.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Acciones cuando el switch de Notificación en pantalla está activado
            } else {
                // Acciones cuando el switch de Notificación en pantalla está desactivado
            }
        }
    }
}
