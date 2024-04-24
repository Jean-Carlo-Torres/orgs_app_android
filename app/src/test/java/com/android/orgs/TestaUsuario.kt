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
            senha = "@#ana2002"
        )
        val usuarioEhValido = usuarioValido.valoresEhValido()

        Assert.assertTrue(usuarioEhValido)
    }

    @Test
    fun testaCadastroDeUsuarioAoInserirOsDadosComSenhaInvalida() {
        val usuarioValido = Usuario(
            id = "ana2002",
            nome = "Ana Amelia",
            senha = "123"
        )
        val usuarioEhValido = usuarioValido.valoresEhValido()

        Assert.assertFalse(usuarioEhValido)
    }

    @Test
    fun testaCadastroDeUsuarioAoInserirOsDadosComIdInvalido() {
        val usuarioValido = Usuario(
            id = "an",
            nome = "Ana Amelia",
            senha = "12345678"
        )
        val usuarioEhValido = usuarioValido.valoresEhValido()

        Assert.assertFalse(usuarioEhValido)
    }

    @Test
    fun testaCadastroDeUsuarioAoInserirOsDadosComNomeInvalido() {
        val usuarioValido = Usuario(
            id = "ana2002",
            nome = "An",
            senha = "12345678"
        )
        val usuarioEhValido = usuarioValido.valoresEhValido()

        Assert.assertFalse(usuarioEhValido)
    }
}