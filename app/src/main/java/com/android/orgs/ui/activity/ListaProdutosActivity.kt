package com.android.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.android.orgs.R
import com.android.orgs.database.OrgsAppDatabase
import com.android.orgs.databinding.ActivityListaProdutosBinding
import com.android.orgs.model.Produto
import com.android.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

private val TAG = "ListaProdutosActivity"

class ListaProdutosActivity : AppCompatActivity() {

    private lateinit var produto: Produto
    private val adapter = ListaProdutosAdapter(context = this)
    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }
    private val produtoDao by lazy {
        OrgsAppDatabase.instancia(this).produtoDao()
    }
    private val usuarioDao by lazy {
        OrgsAppDatabase.instancia(this).usuarioDao()
    }
    private val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e(TAG, "onResume: throwable $throwable")
        Toast.makeText(
            this@ListaProdutosActivity,
            "Falha ao buscar produtos",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        confiruraRecyclerView()
        configuraFab()
        lifecycleScope.launch(handler) {
            launch {
                produtoDao.buscaTodos().collect { produtos ->
                    adapter.atualiza(produtos)
                }
            }
            intent.getStringExtra("CHAVE_USUARIO_ID")?.let { usuarioId ->
                usuarioDao.buscaPorId(usuarioId).collect {
                    Log.i(TAG, "onCreate: $it")
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_ordenacao_lista_produtos, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        lifecycleScope.launch {
            val produtosOrdenado: List<Produto>? = when (item.itemId) {
                R.id.menu_lista_produtos_ordenar_nome_crescente ->
                    produtoDao.ordenarProdutosPorNomeCrescente()

                R.id.menu_lista_produtos_ordenar_nome_decrescente ->
                    produtoDao.ordenarProdutosPorNomeDecrescente()

                R.id.menu_lista_produtos_ordenar_descricao_crescente ->
                    produtoDao.ordenarProdutosPorDescricaoCrescente()

                R.id.menu_lista_produtos_ordenar_descricao_decrescente ->
                    produtoDao.ordenarProdutosPorDescricaoDecrescente()

                R.id.menu_lista_produtos_ordenar_valor_crescente ->
                    produtoDao.ordenarProdutosPorValorCrescente()

                R.id.menu_lista_produtos_ordenar_valor_decrescente ->
                    produtoDao.ordenarProdutosPorValorDecrescente()

                R.id.menu_lista_produtos_ordenar_sem_ordem ->
                    produtoDao.buscaTodos().firstOrNull()

                else -> null
            }

            produtosOrdenado?.let {
                adapter.atualiza(it)
            }
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
            lifecycleScope.launch {
                produtoDao.remove(produto)
                produtoDao.buscaTodos().collect { produtos ->
                    adapter.atualiza(produtos)
                }
            }
        }
    }
}