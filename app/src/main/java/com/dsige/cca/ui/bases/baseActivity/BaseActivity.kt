package com.dsige.cca.ui.bases.baseActivity

import androidx.appcompat.app.AppCompatActivity
import com.dsige.cca.dialogs.LoadingDialog

abstract class BaseActivity : AppCompatActivity() {

    var loadingDialog: LoadingDialog? = null

    init {
        initLoading()
    }

    abstract fun initLoading()

    fun viewLoading(isShow: Boolean, message: String) {
        loadingDialog?.viewLoading(isShow, message)
    }
}