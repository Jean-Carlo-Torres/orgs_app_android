package com.android.orgs.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.android.orgs.model.Fornecedor

@Dao
interface FornecedorDao {

    @Query("SELECT * FROM fornecedor WHERE id = :id")
    fun getById(id: Long): Fornecedor?

    @Insert
    fun insert(fornecedor: Fornecedor)

    @Update
    fun update(fornecedor: Fornecedor)

    @Delete
    fun delete(fornecedor: Fornecedor)

    @Query("SELECT * FROM Fornecedor WHERE title = :title")
    fun getFornecedorByTitle(title: String): Fornecedor?
}