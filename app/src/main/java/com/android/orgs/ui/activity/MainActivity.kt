package com.android.orgs.ui.activity

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.orgs.R
import com.android.orgs.model.Produto
import com.android.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import java.math.BigDecimal

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = ListaProdutosAdapter(
            context = this, produtos = listOf(
                Produto(
                    nome = "Frutas",
                    descricao = "Maça, Banana, Uva",
                    valor = BigDecimal("19.90")
                ),
                Produto(
                    nome = "Pães",
                    descricao = "Integral, Grão, Torradas",
                    valor = BigDecimal("24.90")
                )
            )
        )
        //recyclerView.layoutManager = LinearLayoutManager(this)
    }
}