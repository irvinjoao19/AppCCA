package com.dsige.cca.extensions

import android.os.Build
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import com.dsige.cca.BuildConfig
import com.dsige.cca.helpers.Constants
import com.dsige.cca.extensions.DateExtensions.dateToString
import com.dsige.cca.utils.DateUtils
import java.text.SimpleDateFormat
import java.util.*

object StringExtensions {

    private fun String.getTextHTML(): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(this, Html.FROM_HTML_OPTION_USE_CSS_COLORS)
        } else {
            @Suppress("DEPRECATION") Html.fromHtml(this)
        }
    }

    /**
     * Cuando tenemos un TextView con dos palabras o mas de diferentes colores
     * Ejemplo ->
     * getTextStyleHtml("<font color='#FF0000'>This is </font> html",binding.txtData)
     */
    fun String.getTextStyleHtml(input: AppCompatTextView) {
        input.setText(this.getTextHTML(), TextView.BufferType.SPANNABLE)
    }

    fun String.replaceSpaces(): String = this.replace("\\s+".toRegex(), " ").trim()

    fun String.removeSpaces(): String = this.replace("\\s+".toRegex(), "").trim()

    fun String.viewLogs(message: String) {
        if (BuildConfig.DEBUG) Log.d(this, message)
    }

    fun String.stringToDate(format: String): Date {
        val formato = SimpleDateFormat(format, Locale.getDefault())
        return formato.parse(this) as Date
    }

    fun String.setDateFormat(formatInt: String, formatOut: String): String {
        val formatInt1 = SimpleDateFormat(formatInt, Locale.getDefault())
        val formatOut1 = SimpleDateFormat(formatOut, Locale.getDefault())
        val date = formatInt1.parse(this)
        return formatOut1.format(date as Date)
    }

    fun String.dateQuerySlash(): String {
        val date = DateUtils.getCalendar()
        val dateSplit = this.split("/")
        date.set(Calendar.YEAR, dateSplit[2].toInt())
        date.set(Calendar.MONTH, dateSplit[1].toInt() - 1)
        date.set(Calendar.DAY_OF_MONTH, dateSplit[0].toInt())
        return date.time.dateToString(Constants.Date.PATTERN_DATE_QUERY)
    }

    fun String.dateQueryGuion(): String {
        val date = DateUtils.getCalendar()
        val dateSplit = this.split("-")
        date.set(Calendar.YEAR, dateSplit[0].toInt())
        date.set(Calendar.MONTH, dateSplit[1].toInt() - 1)
        date.set(Calendar.DAY_OF_MONTH, dateSplit[2].toInt())
        return date.time.dateToString(Constants.Date.PATTERN_DATE_VIEW)
    }

    fun String.dateCurrentViewSlash(): Calendar {
        val date = DateUtils.getCalendar()
        val dateSplit = this.split("/")
        date.set(Calendar.DAY_OF_MONTH, dateSplit[0].toInt())
        date.set(Calendar.MONTH, dateSplit[1].toInt() - 1)
        date.set(Calendar.YEAR, dateSplit[2].toInt())
        return date
    }

    private fun String.dateCurrentViewGuion(): Calendar {
        val date = DateUtils.getCalendar()
        val dateSplit = this.split("-")
        date.set(Calendar.YEAR, dateSplit[0].toInt())
        date.set(Calendar.MONTH, dateSplit[1].toInt() - 1)
        date.set(Calendar.DAY_OF_MONTH, dateSplit[2].toInt())
        return date
    }

    fun String?.dateWithHours(): String {
        val calendar = this?.dateCurrentViewGuion()
        if (calendar != null) {
            calendar.set(Calendar.HOUR_OF_DAY, DateUtils.getCalendar().get(Calendar.HOUR_OF_DAY))
            calendar.set(Calendar.MINUTE, DateUtils.getCalendar().get(Calendar.MINUTE))
            calendar.set(Calendar.SECOND, DateUtils.getCalendar().get(Calendar.SECOND))
            calendar.set(Calendar.MILLISECOND, DateUtils.getCalendar().get(Calendar.MILLISECOND))
        }
        return calendar?.time?.dateToString(Constants.Date.PATTERN_DATE_ORIGIN)!!
    }

    fun String.dateWithHoursGlobal(): String {
        val date = this.stringToDate(Constants.Date.PATTERN_DATE_GLOBAL)
        val calendar = DateUtils.getCalendar()
        calendar.time = date
        calendar.set(Calendar.MINUTE, DateUtils.getCalendar().get(Calendar.MINUTE))
        calendar.set(Calendar.SECOND, DateUtils.getCalendar().get(Calendar.SECOND))
        calendar.set(Calendar.MILLISECOND, DateUtils.getCalendar().get(Calendar.MILLISECOND))
        return calendar.time.dateToString(Constants.Date.PATTERN_DATE_GLOBAL)
    }

    fun String.compareDatesViewSlash(): Int {
        val dateNew = this.stringToDate(Constants.Date.PATTERN_DATE_VIEW)
        val currentDateString =
            DateUtils.getCalendar().time.dateToString(Constants.Date.PATTERN_DATE_VIEW)
        val currentDate = currentDateString.stringToDate(Constants.Date.PATTERN_DATE_VIEW)
        return dateNew.compareTo(currentDate)
    }

    fun String.isToday(formatIn: String): Boolean {
        val newDateString = this.setDateFormat(formatIn, Constants.Date.PATTERN_DATE)
        val newDate = newDateString.stringToDate(Constants.Date.PATTERN_DATE)
        val currentDateString =
            DateUtils.getCalendar().time.dateToString(Constants.Date.PATTERN_DATE)
        val currentDate = currentDateString.stringToDate(Constants.Date.PATTERN_DATE)
        return newDate.compareTo(currentDate) == 0
    }

    fun String.capitalizeWords(): String =
        split(" ").joinToString(" ") { it.replaceFirstChar { i -> i.uppercase() } }

    fun String.compareDates(): Int {
        val date1 = this.stringToDate(Constants.Date.PATTERN_DATE_QUERY)
        val date2String =
            DateUtils.getCalendar().time.dateToString(Constants.Date.PATTERN_DATE_QUERY)
        val date2 = date2String.stringToDate(Constants.Date.PATTERN_DATE_QUERY)
        return date1.compareTo(date2)
    }

    fun String.setClickableString(
        color: Int,
        textView: TextView?,
        content: List<String>,
        clickableSpans: List<ClickableSpan>
    ) {
        val spannableString = SpannableString(this)
        content.forEachIndexed { index, element ->
            val firstIndex = this.indexOf(element)
            val endIndex = firstIndex + element.length
            spannableString.setSpan(
                clickableSpans[index], firstIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableString.setSpan(
                ForegroundColorSpan(color), firstIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        textView?.linksClickable = true
        textView?.movementMethod = LinkMovementMethod.getInstance()
        textView?.setText(spannableString, TextView.BufferType.SPANNABLE)
    }

//    fun String.isEmailOrNumber(): String {
//        return if (!Helper.isEmail(this)) "$this@wallyfree.com" else this
//    }
}