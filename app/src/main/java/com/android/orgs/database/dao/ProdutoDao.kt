package com.android.orgs.database.dao

import androidx.room.*
import com.android.orgs.model.Produto

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun buscaTodos(): List<Produto>

    @Insert
    fun salvar(vararg produto: Produto)

    @Delete
    fun remove(produto: Produto)

    @Update
    fun altera(produto: Produto)
}
