package com.android.orgs.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val nome: String,
    val email: String,
    val senha: String
) {
    fun valoresEhValido(): Boolean {
        val nomeIsValid: Boolean =
            nome.isNotEmpty() && nome.matches(Regex("[a-zA-ZÀ-ÖØ-öø-ÿ\\s]+")) && nome.length in 3..16
        val emailIsValid: Boolean =
            email.isNotEmpty() && email.matches(Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"))
        val senhaIsValid: Boolean =
            senha.isNotEmpty() && senha.matches(Regex("^(?=.*[\\p{Punct}])(?=.*[a-zA-Z])(?=.*\\d).{8,16}$")) && senha.length in 8..16

        println("nomeIsValid: $nomeIsValid, emailIsValid: $emailIsValid, senhaIsValid: $senhaIsValid")

        return nomeIsValid && emailIsValid && senhaIsValid
    }
}