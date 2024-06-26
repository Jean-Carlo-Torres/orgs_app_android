package com.android.orgs.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.android.orgs.database.OrgsAppDatabase
import com.android.orgs.model.Fornecedor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FornecedorViewModel(application: Application) : AndroidViewModel(application) {
    private val fornecedorDao = OrgsAppDatabase.instancia(application).fornecedorDao()

    fun cadastrar(fornecedor: Fornecedor) {
        viewModelScope.launch(Dispatchers.IO) {
            val existingFornecedor = fornecedorDao.getFornecedorByTitle(fornecedor.title)
            if (existingFornecedor == null) {
                fornecedorDao.insert(fornecedor)
            }
        }
    }
}