package com.android.orgs.database.dao

import com.android.orgs.model.Usuario
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class UsuarioDaoTests {

    @Test
    fun `deve chamar o dao quando salvar um usuario`() = runBlocking{
        val dao = mockk<UsuarioDao>()
        val usuario = Usuario(
            id = "teste",
            nome = "teste",
            senha = "teste@20002"
        )

        coEvery {
            dao.salva(usuario)
        }.returns(Unit)

        runBlocking {
            dao.salva(usuario)
        }

        coVerify {
            dao.salva(usuario)
        }
    }
}