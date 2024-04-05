package com.android.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.orgs.database.OrgsAppDatabase
import com.android.orgs.databinding.ActivityFormularioProdutoBinding
import com.android.orgs.extensions.tentaCarregarImagem
import com.android.orgs.model.Produto
import com.android.orgs.ui.dialog.FormularioImagemDialog
import java.math.BigDecimal

class FormularioProdutoActivity :
    AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private var url: String? = null
    private var idProduto = 0L

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
        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let { produtoCarregado ->
            title = "Alterar Produto"
            idProduto = produtoCarregado.id
            url = produtoCarregado.imagem
            binding.activityFormularioProdutoImageview.tentaCarregarImagem(produtoCarregado.imagem)
            binding.activityFormularioProdutoNome.setText(produtoCarregado.nome)
            binding.activityFormularioProdutoDescricao.setText(produtoCarregado.descricao)
            binding.activityFormularioProdutoValor.setText(produtoCarregado.valor.toPlainString())
        }
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.activityFormularioProdutoBotaoSalvar
        val db = OrgsAppDatabase.instancia(this)
        val produtoDao = db.produtoDao()

        botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            if (idProduto > 0) {
                produtoDao.altera(produtoNovo)
            } else {
                produtoDao.salvar(produtoNovo)
            }
            finish()
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
            id = idProduto,
            nome = nome,
            descricao = descricao,
            valor = valor,
            imagem = url
        )
    }
}

