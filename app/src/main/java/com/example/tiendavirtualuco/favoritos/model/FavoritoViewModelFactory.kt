package com.example.tiendavirtualuco.favoritos.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tiendavirtualuco.favoritos.repository.FavoritoRepository

class FavoritoViewModelFactory(
    private val favoritoRepository: FavoritoRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritoViewModel::class.java)) {
            return FavoritoViewModel(favoritoRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}