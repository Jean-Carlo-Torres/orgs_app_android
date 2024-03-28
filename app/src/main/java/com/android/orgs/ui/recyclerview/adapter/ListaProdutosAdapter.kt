package com.android.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.orgs.databinding.ProdutoItemBinding
import com.android.orgs.extensions.formatarParaMoedaBrasileira
import com.android.orgs.extensions.tentaCarregarImagem
import com.android.orgs.model.Produto

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto>,
    var quandoClicaNoItem: (produto: Produto) -> Unit = {}
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    inner class ViewHolder(private val binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var produto: Produto

        init {
            itemView.setOnClickListener {
                if (::produto.isInitialized) {
                    quandoClicaNoItem(produto)
                }
            }
        }

        fun vincula(produto: Produto) {
            this.produto = produto
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

            val valorEmMoeda: String = produto.valor.formatarParaMoedaBrasileira()
            valor.text = valorEmMoeda

            binding.imageView.tentaCarregarImagem(produto.imagem)
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
