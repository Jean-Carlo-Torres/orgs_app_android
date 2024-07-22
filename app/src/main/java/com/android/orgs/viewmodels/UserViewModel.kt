package com.android.orgs.viewmodels

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.android.orgs.database.OrgsAppDatabase
import com.android.orgs.database.repository.FornecedorRepository
import com.android.orgs.database.repository.UserRepository
import com.android.orgs.model.Fornecedor
import com.android.orgs.model.Usuario
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository: UserRepository
    private val fornecedorRepository: FornecedorRepository

    init {
        val usuarioDao = OrgsAppDatabase.instancia(application).usuarioDao()
        val fornecedorDao = OrgsAppDatabase.instancia(application).fornecedorDao()

        userRepository = UserRepository(usuarioDao)
        fornecedorRepository = FornecedorRepository(fornecedorDao)
    }

    var user: Usuario? by mutableStateOf(null)

     fun toggleFavorite(fornecedor: Fornecedor) {
        user?.let { usuario ->
            val fornecedor = fornecedor.id?.let { fornecedorRepository.buscaPorId(it) }
            if (fornecedor != null) {
                if (usuario.fornecedoresFavoritos.contains(fornecedor.id)) {
                    removeFornecedorFavorito(fornecedor.id?:0L)
                } else {
                    addFornecedorFavorito(fornecedor.id?:0L)
                }
            }
        }
    }

    fun cadastrarUsuario(usuario: Usuario) = viewModelScope.launch {
        try {
            userRepository.salva(usuario)
            user = usuario
        } catch (e: Exception) {
            Log.i("CadastroUsuario", "configuraBotaoSalvar: $e")
            throw e
        }
    }

    suspend fun autentica(email: String, senha: String): Usuario? {
        val usuario = userRepository.validateUser(email, senha)
        user = usuario
        return usuario
    }

    fun updateUser(usuario: Usuario) = viewModelScope.launch {
        userRepository.atualiza(usuario)
    }

    fun addFornecedorFavorito(fornecedorId: Long) = viewModelScope.launch {
        user?.let { usuario ->
            if(!usuario.fornecedoresFavoritos.contains(fornecedorId)) {
                val updatedFavoritos = usuario.fornecedoresFavoritos.toMutableList()
                updatedFavoritos.add(fornecedorId)
                usuario.fornecedoresFavoritos = updatedFavoritos
                updateUser(usuario)
            }
        }
    }

    fun removeFornecedorFavorito(fornecedorId: Long) = viewModelScope.launch{
        user?.let { usuario ->
            if(usuario.fornecedoresFavoritos.contains(fornecedorId)) {
                val updatedFavoritos = usuario.fornecedoresFavoritos.toMutableList()
                updatedFavoritos.remove(fornecedorId)
                usuario.fornecedoresFavoritos = updatedFavoritos
                updateUser(usuario)
            }
        }
    }

    fun getFornecedoresFavoritosIds(): List<Long> {
        return user?.fornecedoresFavoritos ?: emptyList()
    }
}

