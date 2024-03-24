package com.android.orgs.ui.activity

import android.os.Bundle
import android.util.Log
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
        val botaoSalvar = findViewById<Button>(R.id.botao_salvar)
        botaoSalvar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val campoNome = findViewById<EditText>(R.id.nome)
                val nome = campoNome.text.toString()
                val campoDescricao = findViewById<EditText>(R.id.descricao)
                val descricao = campoDescricao.text.toString()
                val campoValor = findViewById<EditText>(R.id.valor)
                val valorEmTexto = campoValor.text.toString()
                val valor = if (valorEmTexto.isNullOrBlank()) {
                    BigDecimal.ZERO
                } else {
                    BigDecimal(valorEmTexto)
                }

                val produtoNovo = Produto(
                    nome = nome,
                    descricao = descricao,
                    valor = valor
                )
                Log.i("FormularioProdutoActivity", "onCreate: $produtoNovo")
                val dao = ProdutosDAO()
                dao.adicionarProduto(produtoNovo)
                Log.i("FormularioProdutoActivity", "listagem: ${dao.buscaTodos()}")
                finish()
            }
        })
    }
}

