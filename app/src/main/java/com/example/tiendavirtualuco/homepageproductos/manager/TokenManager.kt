package com.example.tiendavirtualuco.homepageproductos.manager

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class TokenManager(context:Context) {
    private val sharedPreferences = EncryptedSharedPreferences.create(
        "secure_prefs",
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveToken(token: String) {
        sharedPreferences.edit().putString("TOKEN_KEY", token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("TOKEN_KEY", null)
    }

    fun clearToken() {
        sharedPreferences.edit().remove("TOKEN_KEY").apply()
    }
}