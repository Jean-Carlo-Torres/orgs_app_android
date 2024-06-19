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
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val usuarioDao = OrgsAppDatabase.instancia(application).usuarioDao()
    var user: Usuario? by mutableStateOf(null)

    fun cadastrarUsuario(usuario: Usuario) {
        viewModelScope.launch {
            try {
                usuarioDao.salva(usuario)
            } catch (e: Exception) {
                Log.i("CadastroUsuario", "configuraBotaoSalvar: $e")
                throw e
            }
        }
    }

     suspend fun autentica(email: String, senha: String):  Usuario?{
        val usuario = usuarioDao.autentica(email, senha)
         user = usuario
         return usuario
    }
}
