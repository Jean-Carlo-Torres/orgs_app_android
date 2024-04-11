package com.android.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.android.orgs.model.Usuario

@Dao
interface UsuarioDao {

    @Insert
    suspend fun salva(usuario: Usuario)
}