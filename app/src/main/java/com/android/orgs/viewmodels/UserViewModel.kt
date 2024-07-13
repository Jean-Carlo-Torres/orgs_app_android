package com.android.orgs.viewmodels

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.android.orgs.database.OrgsAppDatabase
import com.android.orgs.model.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val usuarioDao = OrgsAppDatabase.instancia(application).usuarioDao()
    private val fornecedorDao = OrgsAppDatabase.instancia(application).fornecedorDao()
    var user: Usuario? by mutableStateOf(null)

    init {
        loadLoggedUser()
    }

    private fun loadLoggedUser() = viewModelScope.launch {
        val loggedUser = user?.id?.let { usuarioDao.getLoggedUser(it) }
        user = loggedUser
    }

    fun cadastrarUsuario(usuario: Usuario) = viewModelScope.launch {
        try {
            usuarioDao.salva(usuario)
            user = usuario
        } catch (e: Exception) {
            Log.i("CadastroUsuario", "configuraBotaoSalvar: $e")
            throw e
        }
    }

    suspend fun autentica(email: String, senha: String): Usuario? {
        val usuario = usuarioDao.autentica(email, senha)
        user = usuario
        return usuario
    }

    fun addFornecedorFavorito(fornecedorId: Long) {
        user?.let {
            if (!it.fornecedoresFavoritos.contains(fornecedorId)) {
                it.fornecedoresFavoritos.add(fornecedorId)
                viewModelScope.launch(Dispatchers.IO) {
                    usuarioDao.update(it)
                }
            }
        }
    }

    fun removeFornecedorFavorito(fornecedorId: Long) {
        user?.let {
            if (it.fornecedoresFavoritos.contains(fornecedorId)) {
                it.fornecedoresFavoritos.remove(fornecedorId)
                viewModelScope.launch(Dispatchers.IO) {
                    usuarioDao.update(it)
                }
            }
        }
    }

    fun getFornecedoresFavoritosIds(): List<Long> {
        return user?.fornecedoresFavoritos ?: emptyList()
    }
}

