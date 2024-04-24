package com.android.orgs.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Entity
@Parcelize
data class Produto(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val nome: String,
    val descricao: String,
    val valor: BigDecimal,
    val imagem: String? = null,
    val usuarioId: String? = null
) : Parcelable {

    @Ignore
    val valorEhValido = !valorMenorOuIgualAZero() && !valorMaiorQueMil()

    private fun valorMenorOuIgualAZero(): Boolean {
        return valor <= BigDecimal.ZERO
    }

    private fun valorMaiorQueMil(): Boolean {
        return valor > BigDecimal.valueOf(1000)
    }
}