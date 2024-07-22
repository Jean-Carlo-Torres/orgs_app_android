package com.android.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.android.orgs.model.Usuario

@Dao
interface UsuarioDao {

    @Insert
    suspend fun salva(usuario: Usuario)

    @Query(
        """
        SELECT * FROM Usuario
        WHERE email = :email
        AND senha = :senha
    """
    )
    suspend fun autentica(email: String, senha: String): Usuario?

    @Query("SELECT * FROM Usuario WHERE id = :usuarioId")
    fun buscaPorId(usuarioId: Long): Usuario?

    @Update
    fun update(usuario: Usuario)

    @Query("SELECT * FROM usuario WHERE isLogged = 1 LIMIT 1")
    suspend fun getLoggedUser(): Usuario?
}