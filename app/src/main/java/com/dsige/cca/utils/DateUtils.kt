package com.dsige.cca.utils

import com.dsige.cca.extensions.DateExtensions.dateToString
import com.dsige.cca.extensions.StringExtensions.capitalizeWords
import com.dsige.cca.extensions.StringExtensions.compareDatesViewSlash
import com.dsige.cca.extensions.StringExtensions.isToday
import com.dsige.cca.extensions.StringExtensions.setDateFormat
import com.dsige.cca.helpers.Constants
import com.dsige.cca.helpers.Constants.Date.PATTERN_DATE_QUERY
import com.dsige.cca.helpers.Constants.Date.PATTERN_DATE_QUERY_EXCEL
import com.dsige.cca.helpers.Constants.Date.PATTERN_DATE_WEEK
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun getCalendar(): Calendar = Calendar.getInstance(Locale.getDefault())

    fun getDate(): Date = getCalendar().time

    /**
     *  Obtengo el formato de la fecha del primer dia
     *  @param dateRange suma o resta la fecha actual
     *  @return cadenas del formato :  yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
     *  Podemos agregar otro parametro para retornar diferentes formato de fecha.
     */
    fun getDateFirstDayMonth(dateRange: Int?): String {
        val calendar = Calendar.getInstance()
        if (dateRange != null) {
            calendar.add(Calendar.DATE, dateRange * 30)
        }
        calendar[Calendar.DAY_OF_MONTH] = 1
        return calendar.time.dateToString(Constants.Date.PATTERN_DATE_GLOBAL)
    }

    /**
     *  Obtengo el formato de la fecha del ultimo dia
     *  @param dateRange suma o resta la fecha actual
     *  @return cadenas del formato :  yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
     *  Podemos agregar otro parametro para retornar diferentes formato de fecha.
     */
    fun getDateLastDayMonth(dateRange: Int?,formatIn: String): String {
        val calendar = Calendar.getInstance()
        if (dateRange != null) {
            calendar.add(Calendar.DATE, dateRange * 30)
        }
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        return calendar.time.dateToString(formatIn)
    }

    /**
     *  Obtengo el formato año y mes actual
     *  @param dateRange suma o resta la fecha actual
     *  @return 2022-09 (yyyy-MM)
     */
    fun getCurrentMonthDate(dateRange: Int?): String {
        val date = getCalendar()
        if (dateRange != null) {
            date.add(Calendar.DATE, dateRange * 30)
        }
        return date.time.dateToString(Constants.Date.PATTERN_DATE_YEAR_WEEK)
    }

    /**
     *  Obtengo el nombre del mes en cadena
     *  @param dateRange suma o resta la fecha actual
     *  @return Agosto - 2022 (MMMM - yyyy)
     */
    fun getCurrentMonthDateName(dateRange: Int?): String {
        val calendar = getCalendar()
        if (dateRange != null) {
            calendar.add(Calendar.DATE, dateRange * 30)
        }
        val date = calendar.time.dateToString(Constants.Date.PATTERN_DATE_YEAR_WEEK_NAME)
        return date.replaceFirstChar { it.uppercase() }
    }

    /**
     *  Obtengo el formato de la fecha del primer dia de la semana empezando en Lunes
     *  @param dateRange suma o resta la fecha actual
     *  @return 2022-10-09' (yyyy-MM-dd'T'HH:mm:ss.SSS'Z')
     *  Podemos agregar otro parametro para retornar diferentes formato de fecha.
     */
    fun getDateFirstDayWeek(dateRange: Int?): String {
        val calendar = getCalendar()
        calendar.firstDayOfWeek = Calendar.MONDAY
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        if (dateRange != null) {
            calendar.add(Calendar.DATE, dateRange * 7)
        }
        return calendar.time.dateToString(Constants.Date.PATTERN_DATE_GLOBAL)
    }

    /**
     *  Obtengo el formato de la fecha del primer dia de la semana terminando en Domingo
     *  @param dateRange suma o resta la fecha actual
     *  @return 2022-10-16' (yyyy-MM-dd'T'HH:mm:ss.SSS'Z')
     *  Podemos agregar otro parametro para retornar diferentes formato de fecha.
     */
    fun getDateLastDayWeek(dateRange: Int?,formatIn: String): String {
        val calendar = getCalendar()
        calendar.firstDayOfWeek = Calendar.MONDAY
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        if (dateRange != null) {
            calendar.add(Calendar.DATE, dateRange * 7)
        }
        return calendar.time.dateToString(formatIn)
    }

    fun getCurrentWeekDateName(dateRange: Int?): String {
        val calendarMonday = getCalendar()
        calendarMonday.firstDayOfWeek = Calendar.MONDAY
        calendarMonday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        if (dateRange != null) {
            calendarMonday.add(Calendar.DATE, dateRange * 7)
        }
        val monday = calendarMonday.time.dateToString(PATTERN_DATE_WEEK)

        val calendarSunday = getCalendar()
        calendarSunday.firstDayOfWeek = Calendar.MONDAY
        calendarSunday.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        if (dateRange != null) {
            calendarSunday.add(Calendar.DATE, dateRange * 7)
        }
        val sunday = calendarSunday.time.dateToString(PATTERN_DATE_WEEK)

        return "${monday.capitalizeWords()} al ${sunday.capitalizeWords()}"
    }

    fun getCurrentWeekDateName(value1: String, value2: String, formatIn: String): String {
        val format = SimpleDateFormat(formatIn, Locale.getDefault())
        val monday = format.parse(value1)?.dateToString(Constants.Date.PATTERN_DATE_WEEK)

        val format2 = SimpleDateFormat(Constants.Date.PATTERN_DATE_QUERY, Locale.getDefault())
        val sunday = format2.parse(value2)?.dateToString(Constants.Date.PATTERN_DATE_WEEK)

        return "${monday?.capitalizeWords()} al ${sunday?.capitalizeWords()}"
    }

    fun getCurrentDateName(dateRange: Int?): String {
        val date = getCalendar()
        if (dateRange != null) {
            date.add(Calendar.DATE, dateRange)
        }
        val month = date.time.dateToString(Constants.Date.PATTERN_DATE_MONTH)
        val dayNumber = date.time.dateToString(Constants.Date.PATTERN_DATE_DAY)

        val day = if (date.time.dateToString(Constants.Date.PATTERN_DATE_VIEW)
                .compareDatesViewSlash() == 0
        ) "Hoy" else date.time.dateToString(Constants.Date.PATTERN_DATE_DAY_WEEK_NAME)

        return "${day.replaceFirstChar { it.uppercase() }}, $dayNumber De ${month.replaceFirstChar { it.uppercase() }}"
    }

    /**
     *  Obtengo el formato de la fecha actual
     *  @param dateRange suma o resta la fecha actual
     *  @param formatIn tipo de formato que deseas retornar
     *  @return cadenas del formato :
     *  dd/MM/yyyy
     *  yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
     *  yyyy-MM-dd
     *  etc
     */
    fun getCurrentDate(dateRange: Int?, formatIn: String): String {
        val date = getCalendar()
        if (dateRange != null) {
            date.add(Calendar.DATE, dateRange)
        }
        return date.time.dateToString(formatIn)
    }

    fun getDayWeek(value: String): String {
        val format = SimpleDateFormat(Constants.Date.PATTERN_DATE_QUERY, Locale.getDefault())
        val dayWeek = format.parse(value)?.dateToString(Constants.Date.PATTERN_DATE_DAY_WEEK_NAME)
        return "$dayWeek"
    }

    fun getCurrentDateComplete(
        value: String?, formatIn: String, onResult: (Calendar, String) -> Unit
    ) {
        if (value == null) {
            val date = getCalendar()
            val month = date.time.dateToString(Constants.Date.PATTERN_DATE_MONTH)
            val dayNumber = date.time.dateToString(Constants.Date.PATTERN_DATE_DAY)
            val year = date.time.dateToString(Constants.Date.PATTERN_DATE_YEAR)
            onResult(
                date, "Hoy, $dayNumber ${month.replaceFirstChar { it.uppercase() }} $year"
            )
        } else {
            val format = SimpleDateFormat(formatIn, Locale.getDefault())
            val dayNumber = format.parse(value)?.dateToString(Constants.Date.PATTERN_DATE_DAY)
            val day =
                if (value.isToday(formatIn)) "Hoy" else format.parse(value)
                    ?.dateToString(Constants.Date.PATTERN_DATE_DAY_WEEK_NAME)
            val month = format.parse(value)?.dateToString(Constants.Date.PATTERN_DATE_MONTH)
            val year = format.parse(value)?.dateToString(Constants.Date.PATTERN_DATE_YEAR)
            val calendar = getCalendar()
            val dateSplit = value.setDateFormat(
                formatIn, Constants.Date.PATTERN_DATE_VIEW
            ).split("/")
            calendar.set(Calendar.DAY_OF_MONTH, dateSplit[0].toInt())
            calendar.set(Calendar.MONTH, dateSplit[1].toInt() - 1)
            calendar.set(Calendar.YEAR, dateSplit[2].toInt())
            onResult(
                calendar, "$day, $dayNumber ${month?.replaceFirstChar { it.uppercase() }} $year"
            )
        }
    }

    fun getCurrentDateShort(value: String?): String {
        if (value == null) {
            val date = getCalendar()
            val month = date.time.dateToString(Constants.Date.PATTERN_DATE_MONTH)
            val dayNumber = date.time.dateToString(Constants.Date.PATTERN_DATE_DAY)
            return "Hoy, $dayNumber de ${month.replaceFirstChar { it.uppercase() }}"
        }

        val format = SimpleDateFormat(Constants.Date.PATTERN_DATE_VIEW, Locale.getDefault())
        val dayNumber = format.parse(value)?.dateToString(Constants.Date.PATTERN_DATE_DAY)

        val day = if (value.compareDatesViewSlash() == 0) "Hoy" else format.parse(value)
            ?.dateToString(Constants.Date.PATTERN_DATE_DAY_WEEK_NAME)

        val month = format.parse(value)?.dateToString(Constants.Date.PATTERN_DATE_MONTH)

        return "$day, $dayNumber ${month?.replaceFirstChar { it.uppercase() }}"
    }

    fun getDateFromLong(time: Long, formatIn: String): String {
        val timeZoneUTC: TimeZone = TimeZone.getDefault()
        val offsetFromUTC: Int = timeZoneUTC.getOffset(Date().time) * -1
        val date = Date(time + offsetFromUTC)
        val format = SimpleDateFormat(formatIn, Locale.getDefault())
        return format.format(date)
    }

    fun getDateFromLongCalendar(time: Long, formatIn: String): String {
        val format = SimpleDateFormat(formatIn, Locale.getDefault())
        val calendar = getCalendar()
        calendar.timeInMillis = time
        return format.format(calendar.time)
    }

    fun getCustomDate(time1: Long, time2: Long): String {
        return "${getDateFromLong(time1, Constants.Date.PATTERN_DATE_VIEW)} al ${
            getDateFromLong(time2, Constants.Date.PATTERN_DATE_VIEW)
        }"
    }

    /**
     *  Convierte el formato de fecha yyyy-MM-dd'T'HH:mm:ss.SSS'Z' to dd/MM/yyyy
     *  @param value formato de fecha
     *  @return dd/MM/yyyy
     */
    private fun convertDateGlobalToView(value: String): String? {
        val format = SimpleDateFormat(Constants.Date.PATTERN_DATE_GLOBAL, Locale.getDefault())
        return format.parse(value)?.dateToString(Constants.Date.PATTERN_DATE_VIEW)
    }

    fun getCustomDate(value1: String, value2: String): String {
        return "${convertDateGlobalToView(value1)} al ${convertDateGlobalToView(value2)}"
    }

    fun getCurrentReportExcel(): String {
        val date = getCalendar()
        return date.time.dateToString(PATTERN_DATE_QUERY_EXCEL)
    }

    fun getConvertDate(value1: String?, value2: String?): String {
        val format = SimpleDateFormat(PATTERN_DATE_QUERY, Locale.getDefault())
        val first = value1?.let { format.parse(it)?.dateToString(PATTERN_DATE_WEEK) }

        val format2 = SimpleDateFormat(PATTERN_DATE_QUERY, Locale.getDefault())
        val last = value2?.let { format2.parse(it)?.dateToString(PATTERN_DATE_WEEK) }

        return "$first al $last"
    }
}