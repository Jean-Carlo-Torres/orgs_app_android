package com.android.orgs.database.dao

import androidx.room.*
import com.android.orgs.model.Produto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun buscaTodos(): Flow<List<Produto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvar(vararg produto: Produto)

    @Delete
    suspend fun remove(produto: Produto)

    @Query("SELECT * FROM Produto WHERE id = :id")
    suspend fun buscaPorId(id: Long): Produto?

    @Query("SELECT * FROM Produto ORDER BY nome DESC")
    fun ordenarProdutosPorNomeDecrescente(): List<Produto>

    @Query("SELECT * FROM Produto ORDER BY nome ASC")
    fun ordenarProdutosPorNomeCrescente(): List<Produto>

    @Query("SELECT * FROM Produto ORDER BY descricao DESC")
    fun ordenarProdutosPorDescricaoDecrescente(): List<Produto>

    @Query("SELECT * FROM Produto ORDER BY descricao ASC")
    fun ordenarProdutosPorDescricaoCrescente(): List<Produto>

    @Query("SELECT * FROM Produto ORDER BY valor DESC")
    fun ordenarProdutosPorValorDecrescente(): List<Produto>

    @Query("SELECT * FROM Produto ORDER BY valor ASC")
    fun ordenarProdutosPorValorCrescente(): List<Produto>
}
