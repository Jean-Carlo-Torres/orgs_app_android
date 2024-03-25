package com.android.orgs.dao

import com.android.orgs.model.Produto
import java.math.BigDecimal

class ProdutosDAO {

    fun adicionarProduto(produto: Produto) {
        produtos.add(produto)
    }

    fun buscaTodos(): List<Produto> {
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>(
            Produto(
                nome = "Salada de frutas",
                descricao = "Salada de frutas com legumes",
                valor = BigDecimal("19.90")
            )
        )
    }
}