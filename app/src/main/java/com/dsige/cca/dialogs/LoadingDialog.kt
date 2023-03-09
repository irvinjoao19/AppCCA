package com.dsige.cca.dialogs

import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.annotation.UiThread
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.dsige.cca.R
import com.dsige.cca.databinding.ContentLoadingBinding
import com.dsige.cca.extensions.ActivityExtensions.hideKeyboard
import com.dsige.cca.extensions.StringExtensions.viewLogs

class LoadingDialog(private val activity: AppCompatActivity) {

    private lateinit var binding: ContentLoadingBinding
    private var DIALOG: AlertDialog? = null

    @UiThread
    fun getAlertDialog(): AlertDialog = DIALOG ?: synchronized(this) {
        val dialog by lazy {
            val builder = AlertDialog.Builder(activity, R.style.Core_FullScreen)
            val layoutInflater = LayoutInflater.from(activity)
            binding = ContentLoadingBinding.inflate(layoutInflater)
            builder.setCancelable(false)
            builder.setView(binding.root)
            builder.create()
        }
        DIALOG = dialog
        dialog
    }

    fun viewLoading(isShow: Boolean, message: String) {
        val params = getAlertDialog().window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.MATCH_PARENT
        params?.gravity = Gravity.CENTER
        if (isShow) {
            "LOADINGDIALOG".viewLogs(message)
            activity.hideKeyboard()
            if (!getAlertDialog().isShowing) getAlertDialog().show()
            binding.message.text = message
        } else {
            "LOADINGDIALOG".viewLogs("Dismiss $message")
            getAlertDialog().dismiss()
        }
    }
}