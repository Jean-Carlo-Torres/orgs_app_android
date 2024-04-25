package com.android.orgs

import com.android.orgs.model.Usuario
import org.amshove.kluent.shouldBeFalse
import org.amshove.kluent.shouldBeTrue
import org.junit.Test

class UsuarioTests {

    @Test
    fun `deve retornar true se as informacoes estiverem validas`() {
        val usuarioValido = Usuario(
            id = "ana2002",
            nome = "Ana Amelia",
            senha = "@#ana2002"
        )
        val usuarioEhValido = usuarioValido.valoresEhValido()

        usuarioEhValido.shouldBeTrue()
    }

    @Test
    fun `deve retornar false se a senha for invalida`() {
        val usuarioValido = Usuario(
            id = "ana2002",
            nome = "Ana Amelia",
            senha = "123"
        )
        val usuarioEhValido = usuarioValido.valoresEhValido()

        usuarioEhValido.shouldBeFalse()
    }

    @Test
    fun `deve retornar false se o id for invalida`() {
        val usuarioValido = Usuario(
            id = "an",
            nome = "Ana Amelia",
            senha = "12345678"
        )
        val usuarioEhValido = usuarioValido.valoresEhValido()

        usuarioEhValido.shouldBeFalse()
    }

    @Test
    fun `deve retornar false se o nome for invalida`() {
        val usuarioValido = Usuario(
            id = "ana2002",
            nome = "An",
            senha = "12345678"
        )
        val usuarioEhValido = usuarioValido.valoresEhValido()

        usuarioEhValido.shouldBeFalse()
    }
}