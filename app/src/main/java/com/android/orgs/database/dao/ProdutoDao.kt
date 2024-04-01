package com.android.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.orgs.model.Produto

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun buscaTodos(): List<Produto>

    @Insert
    fun  salvar(vararg produto: Produto)

    @Query("DELETE FROM Produto")
    fun limpar()

    @Query("SELECT * FROM Produto WHERE id = :id")
    fun buscaPorId(id: Int): Produto?

    @Query("SELECT * FROM Produto WHERE nome LIKE :nome")
    fun buscaPorNome(nome: String): List<Produto>
}
