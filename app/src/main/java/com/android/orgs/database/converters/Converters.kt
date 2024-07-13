package com.android.orgs.database.converters

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {
    @TypeConverter
    fun deDouble(valor: Double?): BigDecimal {
        return valor?.let { BigDecimal(valor.toString()) } ?: BigDecimal.ZERO
    }

    @TypeConverter
    fun bigDecimalParaDouble(valor: BigDecimal?): Double? {
        return valor?.let { valor.toDouble() }
    }

    @TypeConverter
    fun fromListToString(list: List<Long>?): String {
        return list?.joinToString(",") ?: ""
    }

    @TypeConverter
    fun fromStringToList(value: String): List<Long> {
        return value.split(",").mapNotNull { it.toLongOrNull() }
    }
}