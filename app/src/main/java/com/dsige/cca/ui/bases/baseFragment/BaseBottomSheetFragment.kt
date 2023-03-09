package com.dsige.cca.ui.bases.baseFragment

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.dsige.cca.ui.bases.baseActivity.BaseActivity

abstract class BaseBottomSheetFragment : BottomSheetDialogFragment() {

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