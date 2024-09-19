package com.example.tiendavirtualuco

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class LanguageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        val languageRadioGroup = findViewById<RadioGroup>(R.id.language_radio_group)
        val applyButton = findViewById<Button>(R.id.button_apply_language)
        val backButton = findViewById<ImageButton>(R.id.btn_back)

        applyButton.setOnClickListener {
            val selectedId = languageRadioGroup.checkedRadioButtonId
            if (selectedId == -1) {

                showToast("Por favor seleccione un idioma")
                return@setOnClickListener
            }

            val selectedRadioButton = findViewById<RadioButton>(selectedId)
            val languageCode = if (selectedRadioButton.id == R.id.language_spanish) "es" else "en"

            changeAppLanguage(languageCode)
            showToast("Language changed to ${if (languageCode == "es") "Español" else "English"}")
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun changeAppLanguage(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

        val sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("language_code", languageCode)
            apply()
        }

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
