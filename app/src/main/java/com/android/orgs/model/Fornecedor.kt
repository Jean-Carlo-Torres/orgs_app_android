package com.android.orgs.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
data class Fornecedor (
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var image: String? = null,
    var title: String,
    var rating: BigDecimal,
    var distance: BigDecimal,
)

