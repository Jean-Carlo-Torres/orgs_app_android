package com.android.orgs

import com.android.orgs.model.Produto
import org.amshove.kluent.shouldBeFalse
import org.amshove.kluent.shouldBeTrue
import org.junit.Test
import java.math.BigDecimal

class ProdutoTests {

    @Test
    fun `deve retornar false quando valor do produto for menor ou igual a zero`() {
        val produtoValido = Produto(
            nome = "Cesta de Frutas",
            descricao = "Cesta de frutas com diversas frutas",
            valor = BigDecimal("10.0")
        )
        val produtoComValorZero = Produto(
            nome = "Cesta de Frutas",
            descricao = "Cesta de frutas com diversas frutas",
            valor = BigDecimal("0.0")
        )
        val produtoComValorNegativo = Produto(
            nome = "Cesta de Frutas",
            descricao = "Cesta de frutas com diversas frutas",
            valor = BigDecimal("-10.0")
        )

        val valorEhValido = produtoValido.valorEhValido
        val valorEhIgualAZero = produtoComValorZero.valorEhValido
        val valorEhMenorQueZero = produtoComValorNegativo.valorEhValido
        
        valorEhValido.shouldBeTrue()
        valorEhIgualAZero.shouldBeFalse()
        valorEhMenorQueZero.shouldBeFalse()
    }

    @Test
    fun `deve retornar false se o valor do produto for maior que mil`() {
        val produtoInvalido = Produto(
            nome = "Cesta de Frutas",
            descricao = "Cesta de frutas com diversas frutas",
            valor = BigDecimal("1005")
        )

        val valorEhValido = produtoInvalido.valorEhValido

        valorEhValido.shouldBeFalse()
    }
}