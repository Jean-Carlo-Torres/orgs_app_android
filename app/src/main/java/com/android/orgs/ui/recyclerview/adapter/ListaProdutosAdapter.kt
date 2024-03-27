package com.android.orgs.ui.recyclerview.adapter

import android.content.Context
import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.orgs.databinding.ProdutoItemBinding
import com.android.orgs.extensions.tentaCarregarImagem
import com.android.orgs.model.Produto
import java.math.BigDecimal
import java.util.*

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    class ViewHolder(private val binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun vincula(produto: Produto) {
            val nome = binding.produtoItemNome
            val descricao = binding.produtoItemDescricao
            val valor = binding.produtoItemValor

            if (produto.nome.isNotBlank()) {
                nome.text = produto.nome
            } else {
                nome.text = "Produto sem nome"
            }

            if (produto.descricao.isNotBlank()) {
                descricao.text = produto.descricao
            } else {
                descricao.text = "Produto sem descrição"
            }

            val valorEmMoeda: String = formataParaMoedaBrasileira(produto.valor)
            valor.text = valorEmMoeda

            binding.imageView.tentaCarregarImagem(produto.imagem)
        }

        private fun formataParaMoedaBrasileira(valor: BigDecimal): String {
            val formatador: NumberFormat = NumberFormat
                .getCurrencyInstance(Locale("pt", "br"))
            return formatador.format(valor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProdutoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = produtos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }

    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }
}
