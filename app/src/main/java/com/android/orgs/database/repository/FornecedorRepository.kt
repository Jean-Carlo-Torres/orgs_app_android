package com.android.orgs.database.repository

import com.android.orgs.database.dao.FornecedorDao
import com.android.orgs.model.Fornecedor

class FornecedorRepository(private val fornecedorDao: FornecedorDao) {

    suspend fun salva(fornecedor: Fornecedor) {
        fornecedorDao.insert(fornecedor)
    }

    fun atualiza(fornecedor: Fornecedor) {
        fornecedorDao.update(fornecedor)
    }

    fun remove(fornecedor: Fornecedor) {
        fornecedorDao.delete(fornecedor)
    }

    suspend fun buscarPorTitulo(titulo: String): Fornecedor? {
        return fornecedorDao.getFornecedorByTitle(titulo)
    }

    fun buscaPorId(id: Long): Fornecedor? {
        return fornecedorDao.getFornecedorById(id)
    }

    suspend fun buscaTodos(): List<Fornecedor> {
        return fornecedorDao.getAll()
    }
}