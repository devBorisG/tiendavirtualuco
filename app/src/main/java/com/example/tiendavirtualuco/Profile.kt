package com.example.tiendavirtualuco

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class Profile : AppCompatActivity() {

    private lateinit var imgUser: ImageView
    private lateinit var bottomNavigationView: BottomNavigationView
    private val REQUEST_CODE_IMAGE_PICKER = 100
    private val REQUEST_CODE_PERMISSION = 101
    private val REQUEST_CODE_EDIT_PROFILE = 102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        // Configura la Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Configura BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.nav_profile  // Marca el ítem de perfil como seleccionado

        // Botón para navegar a la pantalla de edición de perfil
        val btnEditProfile = findViewById<Button>(R.id.btn_edit_profile)
        btnEditProfile.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_EDIT_PROFILE)
        }

        // Botón para navegar a la pantalla de selección de idioma
        val btnLanguageCurrency = findViewById<Button>(R.id.btn_language_currency)
        btnLanguageCurrency.setOnClickListener {
            val intent = Intent(this, LanguageActivity::class.java)
            startActivity(intent)
        }

        // Botón para navegar a la pantalla de feedback
        val btnFeedback = findViewById<Button>(R.id.btn_feedback)
        btnFeedback.setOnClickListener {
            val intent = Intent(this, FeedbackActivity::class.java)
            startActivity(intent)
        }

        // Botón para navegar a la pantalla de referir un amigo
        val btnReferFriend = findViewById<Button>(R.id.btn_refer_friend)
        btnReferFriend.setOnClickListener {
            val intent = Intent(this, ReferFriendActivity::class.java)
            startActivity(intent)
        }

        // Botón para navegar a la pantalla de términos y condiciones
        val btnTermsConditions = findViewById<Button>(R.id.btn_terms_conditions)
        btnTermsConditions.setOnClickListener {
            val intent = Intent(this, TermsConditionsActivity::class.java)
            startActivity(intent)
        }

        // Botón de Logout que muestra el diálogo de confirmación
        val btnLogout = findViewById<Button>(R.id.btn_logout)
        btnLogout.setOnClickListener {
            showLogoutDialog()
        }

        // ImageView para la imagen del usuario
        imgUser = findViewById(R.id.img_user)
        imgUser.setOnClickListener {
            // Solicitar permisos si no están concedidos
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE_PERMISSION)
            } else {
                // Lanzar el selector de imágenes
                openImagePicker()
            }
        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_CODE_IMAGE_PICKER)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_IMAGE_PICKER && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data
            imgUser.setImageURI(selectedImageUri)
        } else if (requestCode == REQUEST_CODE_EDIT_PROFILE && resultCode == RESULT_OK && data != null) {
            // Obtener los datos actualizados
            val firstName = data.getStringExtra("EXTRA_FIRST_NAME")
            val lastName = data.getStringExtra("EXTRA_LAST_NAME")
            val phone = data.getStringExtra("EXTRA_PHONE")

            // Actualizar la vista con la nueva información
            val txtTeamInfo = findViewById<TextView>(R.id.txt_team_info)
            txtTeamInfo.text = "$firstName $lastName\n$phone\nsupport@tradly.com"
        }
    }

    private fun showLogoutDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("Estás seguro de que deseas cerrar sesión?")
            .setCancelable(false)
            .setPositiveButton("Si") { dialog, _ ->
                dialog.dismiss()
                finish() // Cierra la actividad actual
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.cancel()
            }

        val alert = dialogBuilder.create()
        alert.setTitle("Logout")
        alert.show()
    }

    // Manejar la solicitud de permisos
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openImagePicker()
        }
    }

    // Manejar clics en los íconos de la Toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_heart -> {
                // Acción para el ícono de corazón
                true
            }
            R.id.action_cart -> {
                // Acción para el ícono de carrito
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
