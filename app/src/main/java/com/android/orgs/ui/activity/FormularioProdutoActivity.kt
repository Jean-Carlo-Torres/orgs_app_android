package com.android.orgs.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.android.orgs.R
import com.android.orgs.dao.ProdutosDAO
import com.android.orgs.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity :
    AppCompatActivity(R.layout.activity_formulario_produto) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuraBotaoSalvar()
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = findViewById<Button>(R.id.activity_formulario_produto_botao_salvar)
        val dao = ProdutosDAO()
        botaoSalvar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val produtoNovo = criaProduto()
                dao.adicionarProduto(produtoNovo)
                finish()
            }

            private fun criaProduto(): Produto {
                val campoNome = findViewById<EditText>(R.id.activity_formulario_produto_nome)
                val nome = campoNome.text.toString()
                val campoDescricao = findViewById<EditText>(R.id.activity_formulario_produto_descricao)
                val descricao = campoDescricao.text.toString()
                val campoValor = findViewById<EditText>(R.id.activity_formulario_produto_valor)
                val valorEmTexto = campoValor.text.toString()
                val valor = if (valorEmTexto.isNullOrBlank()) {
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
        })
    }
}

