package com.android.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.android.orgs.R
import com.android.orgs.database.OrgsAppDatabase
import com.android.orgs.databinding.ActivityDetalhesProdutoBinding
import com.android.orgs.extensions.formatarParaMoedaBrasileira
import com.android.orgs.extensions.tentaCarregarImagem
import com.android.orgs.extensions.toast
import com.android.orgs.model.Produto
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private val TAG = "DetalhesProdutoActivity"

class DetalhesProdutoActivity : AppCompatActivity() {

    private var produto: Produto? = null
    private var produtoId: Long = 0L
    private val binding by lazy {
        ActivityDetalhesProdutoBinding.inflate(layoutInflater)
    }
    private val produtoDao by lazy {
        OrgsAppDatabase.instancia(this).produtoDao()
    }
    private val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.i(TAG, "CoroutineExceptionHandler: ${throwable.message}")
        toast("Ocorreu um erro ao carregar os dados do produto")
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarProduto()
    }

    override fun onResume() {
        super.onResume()
        buscaProduto()
    }

    private fun buscaProduto() {
        lifecycleScope.launch(handler) {
            try {
                produto = produtoDao.buscaPorId(produtoId)
                withContext(Main) {
                    produto?.let {
                        preencheCampos(it)
                    } ?: finish()
                }
            } catch (e: Exception) {
                finish()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_detalhes_produto_remover -> {
                lifecycleScope.launch(handler) {
                    try {
                        produto?.let {
                            produtoDao.remove(it)
                        }
                        finish()
                    } catch (e: Exception) {
                        finish()
                    }
                }
            }

            R.id.menu_detalhes_produto_editar -> {
                Intent(this, FormularioProdutoActivity::class.java).apply {
                    putExtra(CHAVE_PRODUTO_ID, produtoId)
                    startActivity(this)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun tentaCarregarProduto() {
        produtoId = intent.getLongExtra(CHAVE_PRODUTO_ID, 0L)
    }

    private fun preencheCampos(produtoCarregado: Produto) {
        with(binding) {
            detalhesProdutoImagemview.tentaCarregarImagem(produtoCarregado.imagem)
            detalhesProdutoNome.text = produtoCarregado.nome
            detalhesProdutoDescricao.text = produtoCarregado.descricao
            detalhesProdutoValor.text = produtoCarregado.valor.formatarParaMoedaBrasileira()
        }
    }
}