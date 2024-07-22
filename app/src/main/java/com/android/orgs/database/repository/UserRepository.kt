package com.android.orgs.database.repository

import com.android.orgs.database.dao.UsuarioDao
import com.android.orgs.model.Usuario

class UserRepository(private val usuarioDao: UsuarioDao) {

    suspend fun salva(usuario: Usuario) {
        usuarioDao.salva(usuario)
    }

    fun atualiza(usuario: Usuario) {
        usuarioDao.update(usuario)
    }

    suspend fun validateUser(email: String, senha: String): Usuario? {
        return usuarioDao.autentica(email, senha)
    }

    suspend fun getUser(userId: Long): Usuario? {
        return usuarioDao.buscaPorId(userId)
    }

    suspend fun getLoggedUser(): Usuario? {
        return usuarioDao.getLoggedUser()
    }
}