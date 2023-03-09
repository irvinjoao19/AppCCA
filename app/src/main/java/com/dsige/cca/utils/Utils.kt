package com.dsige.cca.utils

import android.content.Context
import android.content.pm.PackageManager
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import com.dsige.cca.helpers.Constants
import java.io.File
import java.util.regex.Pattern

object Utils {
    private val TAG = Utils::class.java.simpleName

    val patternName: Pattern
        get() = Pattern.compile(Constants.Regex.REGEX_ADD_NAME)

    val patternPhone: Pattern
        get() = Pattern.compile(Constants.Regex.REGEX_ADD_PHONE)

    val patternLower: Pattern
        get() = Pattern.compile(Constants.Regex.LOWER)

    val patternUpper: Pattern
        get() = Pattern.compile(Constants.Regex.UPPER)

    val patternDigits: Pattern
        get() = Pattern.compile(Constants.Regex.DIGITS)

    val patternSize: Pattern
        get() = Pattern.compile(Constants.Regex.SIZE_PASSWORD)

    val patternSizeRuc: Pattern
        get() = Pattern.compile(Constants.Regex.SIZE_RUC)

    val patternCharacter: Pattern
        get() = Pattern.compile(Constants.Regex.CHARACTER)

    val patternPassword: Pattern
        get() = Pattern.compile(Constants.Regex.PASSWORD)

    val patternValidatePass: Pattern
        get() = Pattern.compile(Constants.Regex.VALIDATE_PASS)

    val patternValidateProduct: Pattern
        get() = Pattern.compile(Constants.Regex.VALIDATE_ADD_PROD)

    val patternValidateDecimal: Pattern
        get() = Pattern.compile(Constants.Regex.VALIDATE_DECIMAL)

    val patternValidateDecimalEfectivo: Pattern
        get() = Pattern.compile(Constants.Regex.VALIDATE_DECIMAL_EFECTIVO)

    val patternValidateCodBarra: Pattern
        get() = Pattern.compile(Constants.Regex.VALIDATE_COD_BARRA)

    fun getFolder(context: Context): File {
        val folder = File(context.cacheDir, ".")
        if (!folder.exists()) {
            val success = folder.mkdirs()
            if (!success) {
                folder.mkdir()
            }
        }
        return folder
    }

    fun getFolderExternal(context: Context): File {
        val folder = File(context.getExternalFilesDir(null)!!.absolutePath)
        if (!folder.exists()) {
            val success = folder.mkdirs()
            if (!success) {
                folder.mkdir()
            }
        }
        return folder
    }

    private fun isAppInstalled(context: Context, packageName: String): Boolean {
        return try {
            context.packageManager?.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            true
        } catch (ignored: PackageManager.NameNotFoundException) {
            false
        }
    }

    fun setClickableString(
        color: Int, value: String, textView: TextView?, linksClickable: Boolean,
        content: Array<String>, clickableSpans: Array<ClickableSpan?>
    ) {
        val spannableString = SpannableString(value)
        content.forEachIndexed { index, element ->
            val firstIndex = value.indexOf(element)
            spannableString.setSpan(
                clickableSpans[index], firstIndex,
                firstIndex + element.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableString.setSpan(
                ForegroundColorSpan(color),
                firstIndex,
                firstIndex + element.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        textView?.linksClickable = linksClickable
        textView?.movementMethod = LinkMovementMethod.getInstance()
        textView?.setText(spannableString, TextView.BufferType.SPANNABLE)
    }
}