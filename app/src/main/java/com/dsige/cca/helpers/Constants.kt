package com.dsige.cca.helpers

object Constants {

    object Others {
        const val PAGE_START = 200
        const val TIME_ZONE = 300
        const val AMOUNT_BASE = 700
        const val MIN_CLICK_INTERVAL = 600
        const val ADD_QUANTITY = 1.00
        const val INIT_QUANTITY = 0.00
    }

    object Regex {
        const val REGEX_ADD_NAME = "(([A-Za-zñÑáÁéÉíÍóÓúÚ]{0,1})([A-Za-zñÑáÁéÉíÍóÓúÚ ]{2,80})?)"
        const val REGEX_ADD_PHONE = "(([1-9]{0,1})([0-9]{2,8})?)"
        const val UPPER = "^(?=(?:[^A-Z]+))([a-z0-9!@#\\$%]*)$"
        const val LOWER = "^(?=(?:[^a-z]+))([A-Z0-9!@#\\$%]*)$"
        const val DIGITS = "^(?=(?:[^0-9]+))([A-Za-z!@#\\$%]*)$"
        const val SIZE_PASSWORD = "^([A-Za-z0-9!@#\\$%]{6,50})$"
        const val SIZE_RUC = "^([A-Za-z0-9!@#\\$%]{11})$"
        const val CHARACTER = "^(?=(?:[^!@#\\$%]+))([A-Za-z0-9]*)$"
        const val PASSWORD =
            "^(?=(?:[^a-z]*[a-z]){1})(?=(?:[^A-Z]*[A-Z]){1})(?=(?:[^0-9]*[0-9]){1}).{8,16}$"
        const val VALIDATE_PASS =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])([A-Za-z\\d$@$!%*?&]|[^ ]){8,16}$"
        const val VALIDATE_ADD_PROD =
            "(([A-Za-z0-9]{0,1})([[:word:][[:space:]][ñÑáÁéÉíÍóÓúÚ._-]]{2,99})?)"
        const val VALIDATE_DECIMAL = "(([0-9]{1})([0-9]{0,5})?)(\\.[0-9]{0,2})?"
        const val VALIDATE_DECIMAL_EFECTIVO = "(([0-9]{1})([0-9]{0,5})?)(\\.[0-9]{0,1})?"
        const val VALIDATE_COD_BARRA = "([A-Za-z0-9]{0,20})"
    }

    object Date {
        const val PATTERN_DATE_ORIGIN = "yyyy-MM-dd HH:mm:ss.SSS"
        const val PATTERN_DATE_TIME_SEC = "dd/MM/yyyyHH:mm:ss"
        const val PATTERN_TIME = "HH:mm:ss"
        const val PATTERN_TIM_WIN = "HHmmss"
        const val PATTERN_TIME_A = "hh:mm a"
        const val PATTERN_DATE_TIME_A = "dd/MM/yyyy hh:mm a"
        const val PATTERN_TIME_B = "HH:mm"
        const val PATTERN_DATE_TIME_B = "dd/MM/yyyy HH:mm"
        const val PATTERN_DATE_QUERY_EXCEL = "yyyyMMdd_HHmmss"
        const val PATTERN_DATE_TIME = "yyyyMMddHHmmss"
        const val PATTERN_DATE = "yyyyMMdd"
        const val PATTERN_DATE_QUERY = "yyyy-MM-dd"
        const val PATTERN_SHORT_DATE = "dd/MM/yy"
        const val PATTERN_DATE_VIEW = "dd/MM/yyyy"
        const val PATTERN_DATE_WEEK = "dd MMM"
        const val PATTERN_DATE_YEAR_WEEK = "yyyy-MM"
        const val PATTERN_DATE_YEAR_WEEK_NAME = "MMMM - yyyy"
        const val PATTERN_DATE_YEAR = "yyyy"
        const val PATTERN_DATE_DAY = "d"
        const val PATTERN_DATE_MONTH = "MMMM"
        const val PATTERN_DATE_DAY_WEEK_NAME = "EEEE"
        const val PATTERN_DATE_DAY_AND_MONTH = "dd MMM"
        const val PATTERN_DATE_GLOBAL = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        const val PATTERN_DATE_NUMBER_MONTH_YEAR = "MM/yyyy"


    }

    object ErrorServer {
        const val ERROR_UNEXPECTED = "Error inesperado, consulte con su proveedor"
        const val ERROR_INTERNET = "Verifique su conexión a Internet"
        const val ERROR_TIMEOUT = "Su conexión ha tardado demasiado tiempo en responder"
        const val TITLE_ERROR_SERVER = "Error en el servidor"
        const val TITLE_ERROR_UNEXPECTED = "Error inesperado"
        const val SESSION_EXPIRED = "session_expired"
    }
}