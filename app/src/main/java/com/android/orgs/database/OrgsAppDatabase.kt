package com.android.orgs.database

import android.content.Context
import androidx.room.*
import com.android.orgs.database.converters.Converters
import com.android.orgs.database.dao.ProdutoDao
import com.android.orgs.database.dao.UsuarioDao
import com.android.orgs.model.Produto
import com.android.orgs.model.Usuario

@Database(
    entities = [
        Produto::class,
        Usuario::class
    ],
    version = 3,
    exportSchema = true,
    
    autoMigrations = [
        AutoMigration(from = 2, to = 3)
    ]
)
@TypeConverters(Converters::class)
abstract class OrgsAppDatabase : RoomDatabase() {
    abstract fun produtoDao(): ProdutoDao
    abstract fun usuarioDao(): UsuarioDao

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