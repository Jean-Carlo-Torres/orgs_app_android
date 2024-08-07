package com.android.orgs.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.orgs.model.Produto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun buscaTodos(): Flow<List<Produto>>

    @Query("SELECT * FROM Produto WHERE usuarioId = :idUsuario")
    fun buscaTodosDoUsuario(idUsuario: String): Flow<List<Produto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salvar(vararg produto: Produto)

    @Delete
    suspend fun remove(produto: Produto)

    @Query("SELECT * FROM Produto WHERE id = :id")
    fun buscaPorId(id: Long): Produto?

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
