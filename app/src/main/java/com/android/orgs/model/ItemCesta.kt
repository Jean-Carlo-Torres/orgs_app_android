package com.android.orgs.model

import com.android.orgs.model.enums.TipoDeVenda

class ItemCesta(
    val nome: String,
    val imagem: String? = null,
    val tipoDeVenda: TipoDeVenda,
    val quantidade: Int
)
