package com.dsige.cca.extensions

import java.text.SimpleDateFormat
import java.util.*

object DateExtensions {

    fun Date.dateToString(format: String): String {
        return SimpleDateFormat(format, Locale.getDefault()).format(this)
    }


}