package com.android.orgs.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.orgs.database.converters.Converters
import com.android.orgs.database.dao.FornecedorDao
import com.android.orgs.database.dao.ProdutoDao
import com.android.orgs.database.dao.UsuarioDao
import com.android.orgs.model.Fornecedor
import com.android.orgs.model.Produto
import com.android.orgs.model.Usuario

@Database(
    entities = [
        Produto::class,
        Usuario::class,
        Fornecedor::class
    ],
    version = 6,
    exportSchema = true,

    autoMigrations = [
        AutoMigration(from = 5, to = 6)
    ]
)
@TypeConverters(Converters::class)
abstract class OrgsAppDatabase : RoomDatabase() {
    abstract fun produtoDao(): ProdutoDao
    abstract fun usuarioDao(): UsuarioDao
    abstract fun fornecedorDao(): FornecedorDao

    companion object {
        @Volatile
        private var db: OrgsAppDatabase? = null
        fun instancia(context: Context): OrgsAppDatabase {
            return db ?: Room.databaseBuilder(
                context,
                OrgsAppDatabase::
                class.java,
                "orgs.db"
            ).build()
                .also {
                    db = it
                }
        }
    }
}