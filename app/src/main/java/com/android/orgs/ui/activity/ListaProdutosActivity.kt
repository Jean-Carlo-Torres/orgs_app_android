package com.android.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.android.orgs.R
import com.android.orgs.database.OrgsAppDatabase
import com.android.orgs.databinding.ActivityListaProdutosBinding
import com.android.orgs.model.Produto
import com.android.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListaProdutosActivity : AppCompatActivity() {

    private lateinit var produto: Produto
    private val adapter = ListaProdutosAdapter(context = this)
    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }
    private val produtosDao by lazy {
        OrgsAppDatabase.instancia(this).produtoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        confiruraRecyclerView()
        configuraFab()
    }

    override fun onResume() {
        super.onResume()
        val scope = MainScope()
        scope.launch {
            val produtos = withContext(Dispatchers.IO) {
                produtosDao.buscaTodos()
            }
            adapter.atualiza(produtos)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_ordenacao_lista_produtos, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val produtosOrdenado: List<Produto>? = when (item.itemId) {
            R.id.menu_lista_produtos_ordenar_nome_crescente ->
                produtosDao.ordenarProdutosPorNomeCrescente()

            R.id.menu_lista_produtos_ordenar_nome_decrescente ->
                produtosDao.ordenarProdutosPorNomeDecrescente()

            R.id.menu_lista_produtos_ordenar_descricao_crescente ->
                produtosDao.ordenarProdutosPorDescricaoCrescente()

            R.id.menu_lista_produtos_ordenar_descricao_decrescente ->
                produtosDao.ordenarProdutosPorDescricaoDecrescente()

            R.id.menu_lista_produtos_ordenar_valor_crescente ->
                produtosDao.ordenarProdutosPorValorCrescente()

            R.id.menu_lista_produtos_ordenar_valor_decrescente ->
                produtosDao.ordenarProdutosPorValorDecrescente()

            R.id.menu_lista_produtos_ordenar_sem_ordem ->
                produtosDao.buscaTodos()

            else -> null
        }
        produtosOrdenado?.let {
            adapter.atualiza(it)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun configuraFab() {
        val fab = binding.activityListaProdutosFab
        fab.setOnClickListener {
            vaiParaFormularioProduto()
        }
    }

    private fun vaiParaFormularioProduto() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
    }

    private fun confiruraRecyclerView() {
        val recyclerView = binding.activityListaProdutosRecyclerView
        recyclerView.adapter = adapter
        adapter.quandoClicaNoItem = {
            val intent = Intent(
                this,
                DetalhesProdutoActivity::class.java
            )
                .apply {
                    putExtra(CHAVE_PRODUTO_ID, it.id)
                }
            startActivity(intent)
        }
        adapter.quandoClicaNoBotaoEditar = { produto ->
            val intent = Intent(this, FormularioProdutoActivity::class.java).apply {
                putExtra(CHAVE_PRODUTO_ID, produto.id)
            }
            startActivity(intent)
        }
        adapter.quandoClicaNoBotaoRemover = { produto ->
            val db = OrgsAppDatabase.instancia(this)
            val produtoDao = db.produtoDao()
            produtoDao.remove(produto)
            adapter.atualiza(produtoDao.buscaTodos())
        }

    }
}