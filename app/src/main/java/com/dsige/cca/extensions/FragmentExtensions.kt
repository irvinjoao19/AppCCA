package com.dsige.cca.extensions

import androidx.fragment.app.Fragment
import com.dsige.cca.extensions.ContextExtensions.hideKeyboard

object FragmentExtensions {

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }
}