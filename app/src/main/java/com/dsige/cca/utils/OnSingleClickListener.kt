package com.dsige.cca.utils

import android.os.SystemClock
import android.view.View
import com.dsige.cca.helpers.Constants

abstract class OnSingleClickListener : View.OnClickListener {

    private var mLastClickTime: Long = 0

    override fun onClick(view: View?) {
        val currentClickTime = SystemClock.uptimeMillis()
        val elapsedTime = currentClickTime - mLastClickTime
        mLastClickTime = currentClickTime
        if (elapsedTime <= Constants.Others.MIN_CLICK_INTERVAL) return
        onSingleClick(view)
    }

    abstract fun onSingleClick(v: View?)
}