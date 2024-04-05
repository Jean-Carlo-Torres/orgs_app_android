package com.android.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.orgs.database.OrgsAppDatabase
import com.android.orgs.databinding.ActivityListaProdutosBinding
import com.android.orgs.ui.recyclerview.adapter.ListaProdutosAdapter

private const val TAG = "ListaProdutosActivity"

class ListaProdutosActivity : AppCompatActivity() {

    private val adapter = ListaProdutosAdapter(context = this)
    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        confiruraRecyclerView()
        configuraFab()
    }

    override fun onResume() {
        super.onResume()
        val db = OrgsAppDatabase.instancia(this)
        val produtosDao = db.produtoDao()
        adapter.atualiza(produtosDao.buscaTodos())
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
                    putExtra(CHAVE_PRODUTO, it)
                }
            startActivity(intent)
        }
        adapter.quandoClicaNoBotaoEditar = {
            Log.d(TAG, "quandoClicaNoBotaoEditar: $it")
        }
        adapter.quandoClicaNoBotaoRemover = {
            Log.d(TAG, "quandoClicaNoBotaoDeletar: $it")
        }
    }
}