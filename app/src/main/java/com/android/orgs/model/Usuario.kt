package com.android.orgs.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Usuario(
    @PrimaryKey
    val id: String,
    val nome: String,
    val senha: String
) {
    fun valoresEhValido(): Boolean {
        val isNotEmptyValues: Boolean = id.isNotEmpty() && nome.isNotEmpty() && senha.isNotEmpty()
        val lengthValues: Boolean =
            id.length in 3..16 && nome.length in 3..16 && senha.length in 8..16
        return isNotEmptyValues && lengthValues
    }
}
