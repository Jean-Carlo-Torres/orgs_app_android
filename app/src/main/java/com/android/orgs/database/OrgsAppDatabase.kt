package com.android.orgs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
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
    version = 2,
    exportSchema = true
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
            ).fallbackToDestructiveMigration()
                .build()
                .also {
                    db = it
                }
        }
    }
}