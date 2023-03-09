package com.dsige.cca.extensions

import android.app.Activity
import android.view.View
import com.dsige.cca.extensions.ContextExtensions.hideKeyboard

object ActivityExtensions {

    fun Activity.hideKeyboard() {
        this.application.hideKeyboard(currentFocus ?: View(this))
    }
}