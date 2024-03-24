package com.android.orgs.dao

import com.android.orgs.model.Produto

class ProdutosDAO {

    fun adicionarProduto(produto: Produto) {
        produtos.add(produto)
    }

    fun buscaTodos(): List<Produto> {
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>()
    }
}