package com.android.orgs.viewmodels

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.android.orgs.database.OrgsAppDatabase
import com.android.orgs.model.Fornecedor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FornecedorViewModel(application: Application) : AndroidViewModel(application) {
    private val fornecedorDao = OrgsAppDatabase.instancia(application).fornecedorDao()

    var fornecedorSelecionado: MutableState<Fornecedor?> = mutableStateOf(null)
        private set

    fun getFornecedorById(id: Long) {
        viewModelScope.launch {
            val fornecedor = withContext(Dispatchers.IO) {
                fornecedorDao.getFornecedorById(id)
            }
            fornecedorSelecionado.value = fornecedor
        }
    }

    fun cadastrar(fornecedor: Fornecedor) {
        viewModelScope.launch(Dispatchers.IO) {
            val existingFornecedor = fornecedorDao.getFornecedorByTitle(fornecedor.title)
            if (existingFornecedor == null) {
                fornecedorDao.insert(fornecedor)
            }
        }
    }

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _fornecedor = MutableStateFlow<Fornecedor?>(null)
    val fornecedor: StateFlow<Fornecedor?> get() = _fornecedor
}
