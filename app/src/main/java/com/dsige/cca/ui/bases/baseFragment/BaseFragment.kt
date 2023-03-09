package com.dsige.cca.ui.bases.baseFragment

import androidx.fragment.app.Fragment
import com.dsige.cca.ui.bases.baseActivity.BaseActivity

abstract class BaseFragment : Fragment() {

    private var BASE_ACTIVITY: BaseActivity? = null

    @Synchronized
    private fun getBaseActivity(): BaseActivity = BASE_ACTIVITY ?: synchronized(this) {
        val baseActivity by lazy {
            requireActivity() as BaseActivity
        }
        BASE_ACTIVITY = baseActivity
        baseActivity
    }

    fun viewLoading(isShow: Boolean, message: String) =
        getBaseActivity().viewLoading(isShow, message)

}