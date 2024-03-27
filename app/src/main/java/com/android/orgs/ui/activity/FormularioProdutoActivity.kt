package com.android.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.orgs.R
import com.android.orgs.dao.ProdutosDAO
import com.android.orgs.databinding.ActivityFormularioProdutoBinding
import com.android.orgs.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity :
    AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
        binding.activityFormularioProdutoImageview.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Sucesso")
                .setMessage("Produto cadastrado com sucesso")
                .setView(R.layout.formulario_imagem)
                .setPositiveButton("Ok") { _, _ ->

                }
                .setNegativeButton("Cancelar") { _, _ ->

                }
                .show()
        }
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.activityFormularioProdutoBotaoSalvar
        val dao = ProdutosDAO()
        botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            dao.adicionarProduto(produtoNovo)
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
            nome = nome,
            descricao = descricao,
            valor = valor
        )
    }
}

