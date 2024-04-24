package com.android.orgs

import com.android.orgs.model.Produto
import org.junit.Assert
import org.junit.Test
import java.math.BigDecimal

class TestaProduto {

    @Test
    fun aoCriarUmProdutoComValorMenorOuIgualAZeroDeveRetornarFalso() {
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

        Assert.assertTrue(valorEhValido)
        Assert.assertFalse(valorEhIgualAZero)
        Assert.assertFalse(valorEhMenorQueZero)
    }

    @Test
    fun aoCriarUmProdutoComValorMaiorQueMilDeveRetornarFalso() {
        val produtoInvalido = Produto(
            nome = "Cesta de Frutas",
            descricao = "Cesta de frutas com diversas frutas",
            valor = BigDecimal("1005")
        )

        val valorEhValido = produtoInvalido.valorEhValido

        Assert.assertFalse(valorEhValido)
    }
}