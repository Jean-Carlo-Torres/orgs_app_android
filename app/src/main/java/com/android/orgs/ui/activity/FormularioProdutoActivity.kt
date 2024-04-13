package com.android.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.android.orgs.database.OrgsAppDatabase
import com.android.orgs.databinding.ActivityFormularioProdutoBinding
import com.android.orgs.extensions.tentaCarregarImagem
import com.android.orgs.extensions.toast
import com.android.orgs.model.Produto
import com.android.orgs.preferences.dataStore
import com.android.orgs.preferences.usuarioLogadoPreferences
import com.android.orgs.ui.dialog.FormularioImagemDialog
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigDecimal

private val TAG = "FormularioProdutoActivity"

class FormularioProdutoActivity :
    AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private val produtoDao by lazy {
        OrgsAppDatabase.instancia(this).produtoDao()
    }
    private val usuarioDao by lazy {
        OrgsAppDatabase.instancia(this).usuarioDao()
    }
    private var url: String? = null
    private var produtoId = 0L

    private val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.i(TAG, "CoroutineExceptionHandler: ${throwable.message}")
        toast("Falha ao salvar produto")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastrar Produto"
        configuraBotaoSalvar()

        binding.activityFormularioProdutoImageview.setOnClickListener {
            FormularioImagemDialog(this)
                .mostrarDialog(url) { imagem ->
                    url = imagem
                    binding.activityFormularioProdutoImageview.tentaCarregarImagem(url)
                }
        }
        tentaCarregarProduto()
        lifecycleScope.launch {
            dataStore.data.collect { preferences ->
                preferences[usuarioLogadoPreferences]?.let { usuarioId ->
                    usuarioDao.buscaPorId(usuarioId).collect {
                        Log.i(TAG, "onCreate: $it")
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        tentaBuscarProduto()
    }

    private fun tentaBuscarProduto() {
        lifecycleScope.launch(handler) {
            produtoDao.buscaPorId(produtoId)?.let {
                withContext(Dispatchers.Main) {
                    title = "Alterar Produto"
                    preencheCampos(it)
                }
            }
        }
    }

    private fun tentaCarregarProduto() {
        produtoId = intent.getLongExtra(CHAVE_PRODUTO_ID, 0)
    }

    private fun preencheCampos(produto: Produto) {
        url = produto.imagem
        binding.activityFormularioProdutoImageview.tentaCarregarImagem(produto.imagem)
        binding.activityFormularioProdutoNome.setText(produto.nome)
        binding.activityFormularioProdutoDescricao.setText(produto.descricao)
        binding.activityFormularioProdutoValor.setText(produto.valor.toPlainString())
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.activityFormularioProdutoBotaoSalvar

        botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            lifecycleScope.launch(handler) {
                produtoDao.salvar(produtoNovo)
                finish()
            }
        }
    }

    private fun criaProduto(): Produto {
        val campoNome = binding.activityFormularioProdutoNome
        val nome = campoNome.text.toString()
        val campoDescricao = binding.activityFormularioProdutoDescricao
        val descricao = campoDescricao.text.toString()
        val campoValor = binding.activityFormularioProdutoValor
        val valorEmTexto = campoValor.text.toString()
        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        return Produto(
            id = produtoId,
            nome = nome,
            descricao = descricao,
            valor = valor,
            imagem = url
        )
    }
}

