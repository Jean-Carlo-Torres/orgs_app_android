package com.android.orgs

import com.android.orgs.model.Usuario
import org.junit.Assert
import org.junit.Test

class TestaUsuario {

    @Test
    fun testaCadastroDeUsuarioAoInserirOsDados() {
        val usuarioValido = Usuario(
            id = "ana2002",
            nome = "Ana Amelia",
            senha = "12345678"
        )

        val usuarioEhValido = usuarioValido.valoresEhValido()

        Assert.assertTrue(usuarioEhValido)
    }
}