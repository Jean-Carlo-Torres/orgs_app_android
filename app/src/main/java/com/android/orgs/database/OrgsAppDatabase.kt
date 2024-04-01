package com.android.orgs.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.orgs.database.converters.Converters
import com.android.orgs.database.dao.ProdutoDao
import com.android.orgs.model.Produto

@Database(entities = [Produto::class], version = 1)
@TypeConverters(Converters::class)
abstract class OrgsAppDatabase : RoomDatabase() {
    abstract fun produtoDao(): ProdutoDao
}