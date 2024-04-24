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
        val idIsValid: Boolean = id.isNotEmpty() && id.matches(Regex("[^\\s]+")) && id.length in 3..16
        val nomeIsValid: Boolean = nome.isNotEmpty() && nome.matches(Regex("[a-zA-ZÀ-ÖØ-öø-ÿ\\s]+")) && nome.length in 3..16
        val senhaIsValid: Boolean = senha.isNotEmpty() && senha.matches(Regex("^(?=.*[\\p{Punct}])(?=.*[a-zA-Z])(?=.*\\d).{8,16}$")) && senha.length in 8..16

        println("idIsValid: $idIsValid, nomeIsValid: $nomeIsValid, senhaIsValid: $senhaIsValid")

        return idIsValid && nomeIsValid && senhaIsValid
    }

}
