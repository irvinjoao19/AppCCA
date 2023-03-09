package com.dsige.cca.ui.bases.baseDialog

import androidx.appcompat.app.AppCompatDialogFragment
import com.dsige.cca.ui.bases.baseActivity.BaseActivity

abstract class BaseDialogFragment : AppCompatDialogFragment() {

    private var BASE_ACTIVITY: BaseActivity? = null

    @Synchronized
    private fun getBaseActivity(): BaseActivity = BASE_ACTIVITY ?: synchronized(this) {
        val baseActivity by lazy {
            requireActivity() as BaseActivity
        }
        BASE_ACTIVITY = baseActivity
        baseActivity
    }
}