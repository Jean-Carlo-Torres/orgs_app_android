package com.android.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.android.orgs.R
import com.android.orgs.databinding.ActivityDetalhesProdutoBinding
import com.android.orgs.extensions.formatarParaMoedaBrasileira
import com.android.orgs.extensions.tentaCarregarImagem
import com.android.orgs.model.Produto

private const val TAG = "DetalhesProdutoActivity"

class DetalhesProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetalhesProdutoBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarProduto()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_detalhes_produto_remover -> {
                Log.i(TAG, "onOptionsItemSelected: remover")
            }

            R.id.menu_detalhes_produto_editar -> {
                Log.i(TAG, "onOptionsItemSelected: editar")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun tentaCarregarProduto() {
        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let { produtoCarregado ->
            preencheCampos(produtoCarregado)
        } ?: finish()
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