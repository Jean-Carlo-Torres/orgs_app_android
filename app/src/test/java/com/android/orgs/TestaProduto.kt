package com.android.orgs

import com.android.orgs.model.Produto
import org.junit.Assert
import org.junit.Test
import java.math.BigDecimal

class TestaProduto {

    @Test
    fun aoCriarUmProdutoComValorCertoOValorDeveSerValido() {
        val produtoValido = Produto(
            nome = "Cesta de Frutas",
            descricao = "Cesta de frutas com diversas frutas",
            valor = BigDecimal("10.0")
        )

        val valorEhValido = produtoValido.valorEhValido

        Assert.assertEquals(true, valorEhValido)
    }
}