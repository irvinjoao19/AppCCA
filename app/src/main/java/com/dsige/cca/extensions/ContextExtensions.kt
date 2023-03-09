package com.dsige.cca.extensions

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import java.io.IOException
import java.nio.charset.StandardCharsets

object ContextExtensions {

    const val dniPosition: Int = 0
    const val cForeignersPosition: Int = 1

    fun Context.obtainColor(color: Int): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ContextCompat.getColor(this, color)
        } else {
            this.resources.getColor(color)
        }
    }

    fun Context.obtainDrawable(drawable: Int): Drawable? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ContextCompat.getDrawable(this, drawable)
        } else {
            this.resources.getDrawable(drawable, null)
        }
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun Context.getJsonFromAssets(fileName: String): String? {
        val jsonString: String = try {
            val inputString = this.assets.open(fileName)
            val size = inputString.available()
            val buffer = ByteArray(size)
            inputString.read(buffer)
            inputString.close()
            String(buffer, StandardCharsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }

    fun Context.toastMensaje(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}