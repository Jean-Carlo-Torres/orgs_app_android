package com.android.orgs.viewmodels

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.android.orgs.database.OrgsAppDatabase
import com.android.orgs.model.Produto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProdutoViewModel(application: Application) : AndroidViewModel(application) {

    private val produtoDao = OrgsAppDatabase.instancia(application).produtoDao()

    var produtoSelecionado: MutableState<Produto?> = mutableStateOf(null)
        private set

    private val _produto = MutableStateFlow<Produto?>(null)
    val produto: StateFlow<Produto?> get() = _produto

    fun getProdutoById(id: Long) {
        viewModelScope.launch {
            val produto = withContext(Dispatchers.IO) {
                produtoDao.buscaPorId(id)
            }
            produtoSelecionado.value = produto
        }
    }

    fun salvar(produto: Produto) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                produtoDao.salvar(produto)
            }
        }
    }
}