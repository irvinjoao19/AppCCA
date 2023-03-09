package com.dsige.cca.extensions

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

object DoubleExtensions {

    fun Double.formatDecimal(): String {
        val dfs = DecimalFormatSymbols(Locale.ROOT)
        val format = DecimalFormat("###,###,##0.00", dfs)
        return format.format(this)
    }

    fun Double.formatDecimalForEdit(): String = this.formatDecimal().replace(",", "")

    fun Double.formatQuantity(): String = this.formatDecimal().replace(".00", "").replace(".0", "")

    fun Double.formatQuantityForEdit(): String = this.formatQuantity().replace(",", "")
}