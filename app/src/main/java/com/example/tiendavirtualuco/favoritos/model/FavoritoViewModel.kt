package com.example.tiendavirtualuco.favoritos.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiendavirtualuco.favoritos.repository.FavoritoRepository
import com.example.tiendavirtualuco.persistence.entity.ProductoConFavorito
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritoViewModel(
    private val favoritoRepository: FavoritoRepository
) : ViewModel() {

    private val _favoritos = MutableLiveData<List<ProductoConFavorito>>()
    val favoritos: LiveData<List<ProductoConFavorito>> get() = _favoritos

    fun agregarFavorito(productoId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            favoritoRepository.agregarAFavoritos(productoId)
            cargarFavoritos() // Actualizar favoritos
        }
    }

    fun eliminarFavorito(productoId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            favoritoRepository.eliminarFavorito(productoId)
            cargarFavoritos() // Actualizar favoritos
        }
    }

    fun cargarFavoritos() {
        viewModelScope.launch(Dispatchers.IO) {
            val listaFavoritos = favoritoRepository.obtenerFavoritos()
            _favoritos.postValue(listaFavoritos)
        }
    }

    fun esFavorito(productoId: Int, callback: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val resultado = favoritoRepository.esFavorito(productoId)
            withContext(Dispatchers.Main) {
                callback(resultado)
            }
        }
    }
}
